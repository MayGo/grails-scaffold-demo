package ee.smit.security

import org.apache.directory.groovyldap.LDAP
import org.apache.directory.groovyldap.SearchScope
import grails.util.Holders

/**
 *
 * Service to search users, groups and services from Active Directory
 * http://groovy.dzone.com/articles/programming-ldap-groovy for reference material
 * SearchScope.BASE – searches only base
 * SearchScope.ONE – searches one level below base, excluding base
 * SearchScope.SUB – searches the entire subtree below base, including base
 * Dependency: org.apache.directory:groovyldap:1.0 (only in nexus.smit)
 * @author mart.jarvi@smit.ee
 *
 */

class LdapService {

	final private String applicationsOU = 'OU=AO,OU=Ressursid,OU=ADM TEHNIK-OPERAATOR,DC=meie,DC=politsei,DC=ee'
	final private String usersOU = 'OU=Kasutajad,OU=SMIT,DC=meie,DC=politsei,DC=ee'
	final private String groupsOU = 'OU=Rollid,OU=SMIT,DC=meie,DC=politsei,DC=ee'

	final private LDAP ldapDS = LDAP.newInstance(
			Holders.config.ldap.server,
			Holders.config.ldap.managerDn,
			Holders.config.ldap.managerPassword)

	static transactional = false

	/**
	 * Returns user DN for searching in the Active Directory
	 *
	 * @param userUID
	 * @return
	 */
	String getUserDnByUID(String userUID) {
		def dn
		def results = ldapDS.search("(samaccountname=${userUID})", usersOU, SearchScope.SUB)

		if (results.size() > 0) {
			dn = results.first().dn
			log.debug "found userdn: ${dn}"
		}
		return dn
	}

	/**
	 * Find all organizational groups where User belongs to - supports nested groups
	 * @param organization
	 * @param userUID
	 * @param nested = true
	 * @return
	 */
	List findUserGroups(String organization, String userUID, boolean nested = true) {
		try {
			def dn = getUserDnByUID(userUID)

			def filter = (nested) ? "(member:1.2.840.113556.1.4.1941:=${dn}" : "(member=${dn}"

			return ldapDS.search("(&(objectClass=group)${filter}))",
					"OU=Struktuur,OU=${organization},${groupsOU}",
					SearchScope.SUB).collect { [cn: it.cn, description: it.description, dn: it.dn] }
		} catch (javax.naming.NameNotFoundException e) {
			return []
		}
	}

	/**
	 *
	 * Get all organisational groups for the given organisation
	 * @param organisation
	 * @return
	 */
	List getOrganizationGroups(String organization) {
		try {
			return ldapDS.search('(objectClass=group)',
					"OU=Struktuur,OU=${organization},${groupsOU}",
					SearchScope.SUB).collect {
				[cn: it.cn, description: it.description, dn: it.dn, memberof: it.memberof]
			}
		} catch (javax.naming.NameNotFoundException e) {
			return []
		}
	}

	/**
	 * Search users by different parameters for example (sn,givenname,organisation,email,employeenumber)
	 * Additional parameters can be found in ldap schema in Person class
	 * @param params
	 * @return List
	 */
	List searchUsers(Map params) {
		try {
			if (!params.organization) {
				return []
			}
			def subFilter = ''

			[lastname: 'sn', firstname: 'givenname', email: 'mail', userUID: 'samaccountname'].each {
				if (params[it.key]) {
					subFilter += "(${it.value}=${params[it.key]?.trim()})"
				}
			}

			def filter = (subFilter) ? "(&(objectClass=Person)${subFilter})" : '(objectClass=Person)'

			log.debug "LDAP query: ${filter}"

			return ldapDS.search(filter,
					"OU=${params.organization},${usersOU}",
					SearchScope.SUB).collect {
				[lastname: it.sn, firstname: it.givenname, dn: it.dn, email: it.mail?.toLowerCase(), userUID: it.samaccountname]
			}
		} catch (javax.naming.NameNotFoundException e) {
			return []
		}
	}

	/**
	 *
	 * Returns given application Endpoint URL
	 * @param application
	 * @return String
	 */
	String getApplicationEndPoint(String application) {

		try {
			if (!application) {
				return null
			}
			def app = ldapDS.read("OU=${application.toUpperCase()},${applicationsOU}")

			return app.postaladdress.trim()
		} catch (javax.naming.NameNotFoundException e) {
			log.error e
		}
		return null
	}

	/**
	 * Returns all roles for the given user and given application  - supports nested roles
	 *
	 * @param userUID
	 * @param application
	 * @return
	 */
	List findApplicationRoles(String application, String userUID = null) {
		try {
			if (!application) {
				return []
			}
			if (userUID) {
				def dn = getUserDnByUID(userUID)
				return ldapDS.search("(&(objectClass=group)(member:1.2.840.113556.1.4.1941:=${dn}))",
						"OU=${application.toUpperCase()},${applicationsOU}",
						SearchScope.ONE).collect { "ROLE_${it.cn.toUpperCase()}" }
			}
			return ldapDS.search('(objectClass=group)',
					"OU=${application.toUpperCase()},${applicationsOU}",
					SearchScope.ONE).collect { "ROLE_${it.cn.toUpperCase()}" }

		} catch (javax.naming.NameNotFoundException e) {
			return []
		}
	}
}
