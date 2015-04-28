package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class VisitSearchService {

	Visit queryForVisit(Long visitId) {
		if (!visitId || visitId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Visit visit = Visit.where { id == visitId }.find()
		if (!visit) {
			throw new ResourceNotFound("No Visit found with Id :[$visitId]")
		}
		return visit
	}

	PagedResultList search(VisitSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Visit.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, VisitSearchCommand cmd) {
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
					like('description', searchString + '%')
					// no type defined for date 
				}
			}
			if (cmd.date != null) {
				Date d = cmd.date
				between('date', d, d + 1)
			}
			if (cmd.description){
				ilike('description', cmd.description + '%')
			}
			if (cmd.pets) {
				'in'('pet.id', cmd.pets.collect { (long) it })
			}
			if (cmd.pet != null) {
				eq('pet.id', cmd.pet)
			}
		}
	}
}
