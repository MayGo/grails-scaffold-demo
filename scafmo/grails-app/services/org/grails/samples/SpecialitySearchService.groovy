package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class SpecialitySearchService {

	Speciality queryForSpeciality(Long specialityId) {
		if (!specialityId || specialityId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Speciality speciality = Speciality.where { id == specialityId }.find()
		if (!speciality) {
			throw new ResourceNotFound("No Speciality found with Id :[$specialityId]")
		}
		return speciality
	}

	PagedResultList search(SpecialitySearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Speciality.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, SpecialitySearchCommand cmd) {
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
