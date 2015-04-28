package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class RoleSearchService {

	Role queryForRole(Long roleId) {
		if (!roleId || roleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Role role = Role.where { id == roleId }.find()
		if (!role) {
			throw new ResourceNotFound("No Role found with Id :[$roleId]")
		}
		return role
	}

	PagedResultList search(RoleSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Role.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, RoleSearchCommand cmd) {
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
					like('authority', searchString + '%')
				}
			}
			if (cmd.authority){
				ilike('authority', cmd.authority + '%')
			}
		}
	}
}
