package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class VetSearchService {

	Vet queryForVet(Long vetId) {
		if (!vetId || vetId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Vet vet = Vet.where { id == vetId }.find()
		if (!vet) {
			throw new ResourceNotFound("No Vet found with Id :[$vetId]")
		}
		return vet
	}

	PagedResultList search(VetSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Vet.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, VetSearchCommand cmd) {
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
					like('lastName', searchString + '%')
					like('firstName', searchString + '%')
				}
			}
			if (cmd.firstName){
				ilike('firstName', cmd.firstName + '%')
			}
			if (cmd.lastName){
				ilike('lastName', cmd.lastName + '%')
			}
		}
	}
}
