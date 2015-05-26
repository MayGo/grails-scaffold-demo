package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonCollectionlessSearchService {

	PersonCollectionless queryForRead(Long personCollectionlessId) {
		return queryFor(personCollectionlessId, true)
	}

	PersonCollectionless queryForWrite(Long personCollectionlessId) {
		return queryFor(personCollectionlessId, false)
	}

	private PersonCollectionless queryFor(Long personCollectionlessId, boolean doReadOnly = true) {
		if (!personCollectionlessId || personCollectionlessId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollectionless personCollectionless
		if (doReadOnly) {
			personCollectionless = PersonCollectionless.read(personCollectionlessId)
		} else {
			personCollectionless = PersonCollectionless.get(personCollectionlessId)
		}

		if (!personCollectionless) {
			throw new ResourceNotFound("No PersonCollectionless found with Id :[$personCollectionlessId]")
		}
		return personCollectionless
	}

	PagedResultList search(PersonCollectionlessSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PersonCollectionless.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, PersonCollectionlessSearchCommand cmd) {
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

					if (searchString.isInteger()) {
						eq('age', searchString.toInteger())
					}
				}
			}
			if (cmd.age != null) {
				eq('age', cmd.age)
			}
			if (cmd.name) {
				ilike('name', cmd.name + '%')
			}
			if (cmd.divisions) {
				'in'('division.id', cmd.divisions.collect { (long) it })
			}
			if (cmd.division != null) {
				eq('division.id', cmd.division)
			}
		}
	}
}
