package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class VetSearchService {

	Vet queryForRead(Long vetId) {
		return queryFor(vetId, true)
	}

	Vet queryForWrite(Long vetId) {
		return queryFor(vetId, false)
	}

	private Vet queryFor(Long vetId, boolean doReadOnly = true) {
		if (!vetId || vetId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Vet vet
		if (doReadOnly) {
			vet = Vet.read(vetId)
		} else {
			vet = Vet.get(vetId)
		}

		if (!vet) {
			throw new ResourceNotFound("No Vet found with Id :[$vetId]")
		}
		return vet
	}

	PagedResultList search(VetSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Vet.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, VetSearchCommand cmd) {
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
					like('lastName', searchString + '%')
					like('firstName', searchString + '%')
				}
			}
			if (cmd.firstName) {
				ilike('firstName', cmd.firstName + '%')
			}
			if (cmd.lastName) {
				ilike('lastName', cmd.lastName + '%')
			}
		}
	}
}
