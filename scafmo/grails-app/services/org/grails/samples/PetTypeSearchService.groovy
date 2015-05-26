package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class PetTypeSearchService {

	PetType queryForRead(Long petTypeId) {
		return queryFor(petTypeId, true)
	}

	PetType queryForWrite(Long petTypeId) {
		return queryFor(petTypeId, false)
	}

	private PetType queryFor(Long petTypeId, boolean doReadOnly = true) {
		if (!petTypeId || petTypeId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PetType petType
		if (doReadOnly) {
			petType = PetType.read(petTypeId)
		} else {
			petType = PetType.get(petTypeId)
		}

		if (!petType) {
			throw new ResourceNotFound("No PetType found with Id :[$petTypeId]")
		}
		return petType
	}

	PagedResultList search(PetTypeSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PetType.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, PetTypeSearchCommand cmd) {
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
					like('name', searchString + '%')
				}
			}
			if (cmd.name) {
				ilike('name', cmd.name + '%')
			}
		}
	}
}
