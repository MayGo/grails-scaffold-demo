package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class PetSearchService {

	Pet queryForPet(Long petId) {
		if (!petId || petId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Pet pet = Pet.where { id == petId }.find()
		if (!pet) {
			throw new ResourceNotFound("No Pet found with Id :[$petId]")
		}
		return pet
	}

	PagedResultList search(PetSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Pet.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, PetSearchCommand cmd) {
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
					// no type defined for birthDate 
				}
			}
			if (cmd.birthDate != null) {
				Date d = cmd.birthDate
				between('birthDate', d, d + 1)
			}
			if (cmd.name){
				ilike('name', cmd.name + '%')
			}
			if (cmd.types) {
				'in'('type.id', cmd.types.collect { (long) it })
			}
			if (cmd.type != null) {
				eq('type.id', cmd.type)
			}
			if (cmd.owners) {
				'in'('owner.id', cmd.owners.collect { (long) it })
			}
			if (cmd.owner != null) {
				eq('owner.id', cmd.owner)
			}
		}
	}
}
