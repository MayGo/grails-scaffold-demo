package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class UserRoleSearchService {

	UserRole queryForRead(Long userRoleId) {
		return queryFor(userRoleId, true)
	}

	UserRole queryForWrite(Long userRoleId) {
		return queryFor(userRoleId, false)
	}

	private UserRole queryFor(Long userRoleId, boolean doReadOnly = true) {
		if (!userRoleId || userRoleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		UserRole userRole
		if (doReadOnly) {
			userRole = UserRole.read(userRoleId)
		} else {
			userRole = UserRole.get(userRoleId)
		}

		if (!userRole) {
			throw new ResourceNotFound("No UserRole found with Id :[$userRoleId]")
		}
		return userRole
	}

	PagedResultList search(UserRoleSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) UserRole.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, UserRoleSearchCommand cmd) {
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
