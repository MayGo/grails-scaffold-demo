package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
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

	PagedResultList search(UserRoleSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) UserRole.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, UserRoleSearchCommand cmd) {
		String searchString = cmd.searchString

		builder.with {
			//readOnly true
			if (cmd.id) {
				eq('id', cmd.id)
			}
			if (searchString) {
				or {
					eq('id', -1L)

					if (searchString.isLong()) {
						eq('id', searchString.toLong())
					}
				}
			}
			if (cmd.roles) {
				'in'('role.id', cmd.roles.collect { (long) it })
			}
			if (cmd.role != null) {
				eq('role.id', cmd.role)
			}
			if (cmd.users) {
				'in'('user.id', cmd.users.collect { (long) it })
			}
			if (cmd.user != null) {
				eq('user.id', cmd.user)
			}
		}
	}
}
