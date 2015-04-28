package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonSearchService {

	Person queryForPerson(Long personId) {
		if (!personId || personId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Person person = Person.where { id == personId }.find()
		if (!person) {
			throw new ResourceNotFound("No Person found with Id :[$personId]")
		}
		return person
	}

	PagedResultList search(PersonSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Person.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, PersonSearchCommand cmd) {
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
