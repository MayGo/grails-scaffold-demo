package scafmo.security

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class UserSearchService {

	User queryForRead(Long userId) {
		return queryFor(userId, true)
	}

	User queryForWrite(Long userId) {
		return queryFor(userId, false)
	}

	private User queryFor(Long userId, boolean doReadOnly = true) {
		if (!userId || userId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		User user
		if (doReadOnly) {
			user = User.read(userId)
		} else {
			user = User.get(userId)
		}

		if (!user) {
			throw new ResourceNotFound("No User found with Id :[$userId]")
		}
		return user
	}

	PagedResultList search(UserSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) User.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, UserSearchCommand cmd) {
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
					like('username', searchString + '%')

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('passwordExpired', searchString.toBoolean())
					}

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('enabled', searchString.toBoolean())
					}
				}
			}
			if (cmd.accountExpired != null) {
				eq('accountExpired', cmd.accountExpired)
			}
			if (cmd.accountLocked != null) {
				eq('accountLocked', cmd.accountLocked)
			}
			if (cmd.enabled != null) {
				eq('enabled', cmd.enabled)
			}
			if (cmd.passwordExpired != null) {
				eq('passwordExpired', cmd.passwordExpired)
			}
			if (cmd.username) {
				ilike('username', cmd.username + '%')
			}
		}
	}
}
