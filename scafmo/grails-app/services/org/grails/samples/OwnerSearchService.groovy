package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class OwnerSearchService {

	Owner queryForOwner(Long ownerId) {
		if (!ownerId || ownerId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Owner owner = Owner.where { id == ownerId }.find()
		if (!owner) {
			throw new ResourceNotFound("No Owner found with Id :[$ownerId]")
		}
		return owner
	}

	PagedResultList search(OwnerSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Owner.createCriteria()
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

	private void searchCriteria(BuildableCriteria builder, OwnerSearchCommand cmd) {
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
					like('telephone', searchString + '%')
					like('lastName', searchString + '%')
					like('firstName', searchString + '%')
				}
			}
			if (cmd.address){
				ilike('address', cmd.address + '%')
			}
			if (cmd.city){
				ilike('city', cmd.city + '%')
			}
			if (cmd.firstName){
				ilike('firstName', cmd.firstName + '%')
			}
			if (cmd.lastName){
				ilike('lastName', cmd.lastName + '%')
			}
			if (cmd.telephone){
				ilike('telephone', cmd.telephone + '%')
			}
		}
	}
}
