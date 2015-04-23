package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
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

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Owner.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: params.offset,
				max: params.max,
				order: params.order,
				sort: params.sort
		) {
			searchCriteria criteriaBuilder, params
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, Map params) {
		String searchString = params.searchString
		JSONElement filter = params.filter ? JSON.parse(params.filter.toString()) : new JSONObject()

		builder.with {
			//readOnly true

			if (filter['id']) {
				eq('id', filter['id'].toString().toLong())
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
			if (filter['address']) {
				ilike('address', "${filter['address']}%")
			}
			if (filter['city']) {
				ilike('city', "${filter['city']}%")
			}
			if (filter['firstName']) {
				ilike('firstName', "${filter['firstName']}%")
			}
			if (filter['lastName']) {
				ilike('lastName', "${filter['lastName']}%")
			}
			if (filter['telephone']) {
				ilike('telephone', "${filter['telephone']}%")
			}
		}
	}
}
