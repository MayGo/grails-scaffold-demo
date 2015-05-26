package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class RoleSearchService {

	Role queryForRead(Long roleId) {
		return queryFor(roleId, true)
	}

	Role queryForWrite(Long roleId) {
		return queryFor(roleId, false)
	}

	private Role queryFor(Long roleId, boolean doReadOnly = true) {
		if (!roleId || roleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Role role
		if (doReadOnly) {
			role = Role.read(roleId)
		} else {
			role = Role.get(roleId)
		}

		if (!role) {
			throw new ResourceNotFound("No Role found with Id :[$roleId]")
		}
		return role
	}

	PagedResultList search(RoleSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Role.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
			readOnly(doReadOnly)
		}

		return results
	}

	// TODO: Refactor and cleanup code so Codenarc check passes dynamic pgJsonHasFieldValue
	@SuppressWarnings(['AbcMetric', 'CyclomaticComplexity', 'MethodSize'])
	@GrailsCompileStatic(TypeCheckingMode.SKIP) // We want to use dynamically added criterias, eg: pgJsonHasFieldValue
	private void searchCriteria(BuildableCriteria builder, RoleSearchCommand cmd) {
		String searchString = cmd.searchString
		builder.with {
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
			if (cmd.authority) {
				ilike('authority', cmd.authority + '%')
			}
		}
	}
}
