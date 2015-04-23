package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class UserSearchService {

	User queryForUser(Long userId) {
		if (!userId || userId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		User user = User.where { id == userId }.find()
		if (!user) {
			throw new ResourceNotFound("No User found with Id :[$userId]")
		}
		return user
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) User.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: params.offset,
				max: params.max,
				order: params.order,
				sort: params.sort
		) {
			searchCriteria criteriaBuilder, params
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, Map params) {
		String searchString = params.searchString
		JSONElement filter = params.filter ? JSON.parse(params.filter.toString()) : new JSONObject()

		builder.with {
			//readOnly true

			if (filter['id']) {
				eq('id', filter['id'].toString().toLong())
			}

			if (searchString) {
				or {
					eq('id', -1L)

					if (searchString.isLong()) {
						eq('id', searchString.toLong())
					}
					like('username', searchString + '%')

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('passwordExpired', searchString.toBoolean())
					}

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('enabled', searchString.toBoolean())
					}
				}
			}
			if (filter['accountExpired']) {
				eq('accountExpired', filter['accountExpired'].toString().toBoolean())
			}
			if (filter['accountLocked']) {
				eq('accountLocked', filter['accountLocked'].toString().toBoolean())
			}
			if (filter['enabled']) {
				eq('enabled', filter['enabled'].toString().toBoolean())
			}
			if (filter['passwordExpired']) {
				eq('passwordExpired', filter['passwordExpired'].toString().toBoolean())
			}
			if (filter['username']) {
				ilike('username', "${filter['username']}%")
			}
		}
	}
}
