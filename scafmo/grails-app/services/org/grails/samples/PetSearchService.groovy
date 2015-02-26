package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON
import grails.orm.HibernateCriteriaBuilder
import grails.orm.PagedResultList
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

//@GrailsCompileStatic
@Transactional(readOnly = true)
class PetSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) Pet.createCriteria()
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

	private void searchCriteria(HibernateCriteriaBuilder builder, Map params) {
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


					if(searchString.isLong()){
						eq('id', searchString.toLong())
					}
					like('name', searchString + '%')
					// no type defined for birthDate 
				}
			}

			if (filter['birthDate']) {
				String inputFormat = "yyyy-MM-dd HH:mm:ss.SSSZ"
				Date d = Date.parse(inputFormat, filter['birthDate'].toString())
				between('birthDate', d, d)
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
