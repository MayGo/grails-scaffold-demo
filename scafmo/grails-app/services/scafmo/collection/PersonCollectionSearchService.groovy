package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonCollectionSearchService {

	PersonCollection queryForRead(Long personCollectionId) {
		return queryFor(personCollectionId, true)
	}

	PersonCollection queryForWrite(Long personCollectionId) {
		return queryFor(personCollectionId, false)
	}

	private PersonCollection queryFor(Long personCollectionId, boolean doReadOnly = true) {
		if (!personCollectionId || personCollectionId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollection personCollection
		if (doReadOnly) {
			personCollection = PersonCollection.read(personCollectionId)
		} else {
			personCollection = PersonCollection.get(personCollectionId)
		}

		if (!personCollection) {
			throw new ResourceNotFound("No PersonCollection found with Id :[$personCollectionId]")
		}
		return personCollection
	}

	PagedResultList search(PersonCollectionSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PersonCollection.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, PersonCollectionSearchCommand cmd) {
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
