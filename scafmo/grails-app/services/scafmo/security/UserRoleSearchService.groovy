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
class UserRoleSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) UserRole.createCriteria()
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
				}
			}

			if (filter['roles']) {
				'in'('role.id', filter['roles'].collect { (long) it })
			}
			if (filter['role']) {
				eq('role.id', (long) filter['role'])
			}

			if (filter['users']) {
				'in'('user.id', filter['users'].collect { (long) it })
			}
			if (filter['user']) {
				eq('user.id', (long) filter['user'])
			}
		}
	}
}
