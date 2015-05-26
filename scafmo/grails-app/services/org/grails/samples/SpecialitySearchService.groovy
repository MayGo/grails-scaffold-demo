package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class SpecialitySearchService {

	Speciality queryForRead(Long specialityId) {
		return queryFor(specialityId, true)
	}

	Speciality queryForWrite(Long specialityId) {
		return queryFor(specialityId, false)
	}

	private Speciality queryFor(Long specialityId, boolean doReadOnly = true) {
		if (!specialityId || specialityId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Speciality speciality
		if (doReadOnly) {
			speciality = Speciality.read(specialityId)
		} else {
			speciality = Speciality.get(specialityId)
		}

		if (!speciality) {
			throw new ResourceNotFound("No Speciality found with Id :[$specialityId]")
		}
		return speciality
	}

	PagedResultList search(SpecialitySearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Speciality.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, SpecialitySearchCommand cmd) {
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
