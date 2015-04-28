package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonCollectionlessSearchService {

	PersonCollectionless queryForPersonCollectionless(Long personCollectionlessId) {
		if (!personCollectionlessId || personCollectionlessId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollectionless personCollectionless = PersonCollectionless.where { id == personCollectionlessId }.find()
		if (!personCollectionless) {
			throw new ResourceNotFound("No PersonCollectionless found with Id :[$personCollectionlessId]")
		}
		return personCollectionless
	}

	PagedResultList search(PersonCollectionlessSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PersonCollectionless.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, PersonCollectionlessSearchCommand cmd) {
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

					if (searchString.isInteger()) {
						eq('age', searchString.toInteger())
					}
				}
			}
			if (cmd.age != null) {
				eq('age', cmd.age)
			}
			if (cmd.name){
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
