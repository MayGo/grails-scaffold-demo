package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonSearchService {

	Person queryForRead(Long personId) {
		return queryFor(personId, true)
	}

	Person queryForWrite(Long personId) {
		return queryFor(personId, false)
	}

	private Person queryFor(Long personId, boolean doReadOnly = true) {
		if (!personId || personId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Person person
		if (doReadOnly) {
			person = Person.read(personId)
		} else {
			person = Person.get(personId)
		}

		if (!person) {
			throw new ResourceNotFound("No Person found with Id :[$personId]")
		}
		return person
	}

	PagedResultList search(PersonSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Person.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, PersonSearchCommand cmd) {
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
