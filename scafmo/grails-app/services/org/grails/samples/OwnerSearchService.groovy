package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class OwnerSearchService {

	Owner queryForRead(Long ownerId) {
		return queryFor(ownerId, true)
	}

	Owner queryForWrite(Long ownerId) {
		return queryFor(ownerId, false)
	}

	private Owner queryFor(Long ownerId, boolean doReadOnly = true) {
		if (!ownerId || ownerId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Owner owner
		if (doReadOnly) {
			owner = Owner.read(ownerId)
		} else {
			owner = Owner.get(ownerId)
		}

		if (!owner) {
			throw new ResourceNotFound("No Owner found with Id :[$ownerId]")
		}
		return owner
	}

	PagedResultList search(OwnerSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Owner.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, OwnerSearchCommand cmd) {
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
					like('telephone', searchString + '%')
					like('lastName', searchString + '%')
					like('firstName', searchString + '%')
				}
			}
			if (cmd.address) {
				ilike('address', cmd.address + '%')
			}
			if (cmd.city) {
				ilike('city', cmd.city + '%')
			}
			if (cmd.firstName) {
				ilike('firstName', cmd.firstName + '%')
			}
			if (cmd.lastName) {
				ilike('lastName', cmd.lastName + '%')
			}
			if (cmd.telephone) {
				ilike('telephone', cmd.telephone + '%')
			}
		}
	}
}
