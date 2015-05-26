package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class VisitSearchService {

	Visit queryForRead(Long visitId) {
		return queryFor(visitId, true)
	}

	Visit queryForWrite(Long visitId) {
		return queryFor(visitId, false)
	}

	private Visit queryFor(Long visitId, boolean doReadOnly = true) {
		if (!visitId || visitId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Visit visit
		if (doReadOnly) {
			visit = Visit.read(visitId)
		} else {
			visit = Visit.get(visitId)
		}

		if (!visit) {
			throw new ResourceNotFound("No Visit found with Id :[$visitId]")
		}
		return visit
	}

	PagedResultList search(VisitSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Visit.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, VisitSearchCommand cmd) {
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
					like('description', searchString + '%')
					// no type defined for date 
				}
			}
			if (cmd.date != null) {
				Date d = cmd.date
				between('date', d, d + 1)
			}
			if (cmd.description) {
				ilike('description', cmd.description + '%')
			}
			if (cmd.pets) {
				'in'('pet.id', cmd.pets.collect { (long) it })
			}
			if (cmd.pet != null) {
				eq('pet.id', cmd.pet)
			}
		}
	}
}
