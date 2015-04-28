package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class PetTypeSearchService {

	PetType queryForPetType(Long petTypeId) {
		if (!petTypeId || petTypeId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PetType petType = PetType.where { id == petTypeId }.find()
		if (!petType) {
			throw new ResourceNotFound("No PetType found with Id :[$petTypeId]")
		}
		return petType
	}

	PagedResultList search(PetTypeSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PetType.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, PetTypeSearchCommand cmd) {
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
		}
	}
}
