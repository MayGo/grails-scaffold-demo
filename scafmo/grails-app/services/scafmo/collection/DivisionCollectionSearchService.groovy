package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class DivisionCollectionSearchService {

	DivisionCollection queryForRead(Long divisionCollectionId) {
		return queryFor(divisionCollectionId, true)
	}

	DivisionCollection queryForWrite(Long divisionCollectionId) {
		return queryFor(divisionCollectionId, false)
	}

	private DivisionCollection queryFor(Long divisionCollectionId, boolean doReadOnly = true) {
		if (!divisionCollectionId || divisionCollectionId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollection divisionCollection
		if (doReadOnly) {
			divisionCollection = DivisionCollection.read(divisionCollectionId)
		} else {
			divisionCollection = DivisionCollection.get(divisionCollectionId)
		}

		if (!divisionCollection) {
			throw new ResourceNotFound("No DivisionCollection found with Id :[$divisionCollectionId]")
		}
		return divisionCollection
	}

	PagedResultList search(DivisionCollectionSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) DivisionCollection.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, DivisionCollectionSearchCommand cmd) {
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
			if (cmd.headDivisions) {
				'in'('headDivision.id', cmd.headDivisions.collect { (long) it })
			}
			if (cmd.headDivision != null) {
				eq('headDivision.id', cmd.headDivision)
			}
		}
	}
}
