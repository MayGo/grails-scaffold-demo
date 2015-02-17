package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON
import grails.orm.HibernateCriteriaBuilder
import grails.orm.PagedResultList
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

@GrailsCompileStatic
@Transactional(readOnly = true)
class UserSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) User.createCriteria()
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

	private void searchCriteria(HibernateCriteriaBuilder builder, Map params) {
		String searchString = params.searchString
		JSONElement filter = params.filter ? JSON.parse(params.filter.toString()) : new JSONObject()

		builder.with {
			//readOnly true

			if (filter['id']) {
				eq('id', filter['id'].toString().toLong())
			}

			if (searchString) {
				or {

					if(searchString.isLong()){
						eq('id', searchString.toLong())
					}

					eq('accountExpired', searchString.toBoolean())
					

					eq('accountLocked', searchString.toBoolean())
					

					eq('enabled', searchString.toBoolean())
					
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
