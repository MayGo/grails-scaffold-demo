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

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Pet.createCriteria()
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
					like('name', searchString + '%')
					// no type defined for birthDate 
				}
			}

			if (filter['birthDate']) {
				Date d
				if (filter['birthDate'].toString().isNumber()) {
					d = new Date(filter['birthDate'].toString().toLong())
				} else {
					String inputFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
					d = Date.parse(inputFormat, filter['birthDate'].toString())
				}

				between('birthDate', d, d + 1)
			}
			if (filter['name']) {
				ilike('name', "${filter['name']}%")
			}

			if (filter['types']) {
				'in'('type.id', filter['types'].collect { (long) it })
			}
			if (filter['type']) {
				eq('type.id', (long) filter['type'])
			}

			if (filter['owners']) {
				'in'('owner.id', filter['owners'].collect { (long) it })
			}
			if (filter['owner']) {
				eq('owner.id', (long) filter['owner'])
			}
		}
	}
}
