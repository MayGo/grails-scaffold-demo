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
class UserRoleSearchService {

	UserRole queryForUserRole(Long userRoleId) {
		if (!userRoleId || userRoleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		UserRole userRole = UserRole.where { id == userRoleId }.find()
		if (!userRole) {
			throw new ResourceNotFound("No UserRole found with Id :[$userRoleId]")
		}
		return userRole
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) UserRole.createCriteria()
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
