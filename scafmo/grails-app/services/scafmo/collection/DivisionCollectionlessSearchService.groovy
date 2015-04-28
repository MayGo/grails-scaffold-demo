package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class DivisionCollectionlessSearchService {

	DivisionCollectionless queryForDivisionCollectionless(Long divisionCollectionlessId) {
		if (!divisionCollectionlessId || divisionCollectionlessId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollectionless divisionCollectionless = DivisionCollectionless.where { id == divisionCollectionlessId }.find()
		if (!divisionCollectionless) {
			throw new ResourceNotFound("No DivisionCollectionless found with Id :[$divisionCollectionlessId]")
		}
		return divisionCollectionless
	}

	PagedResultList search(DivisionCollectionlessSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) DivisionCollectionless.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, DivisionCollectionlessSearchCommand cmd) {
		String searchString = cmd.searchString

		builder.with {
			//readOnly true
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
			if (cmd.name){
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
