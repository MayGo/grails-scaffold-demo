package org.grails.samples

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.datastore.mapping.query.api.BuildableCriteria


//@GrailsCompileStatic
@Transactional(readOnly = true)
class VisitSearchService {

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Visit.createCriteria()
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
					like('description', searchString + '%')
					// no type defined for date 
				}
			}

			if (filter['date']) {
				String inputFormat = "yyyy-MM-dd HH:mm:ss.SSSZ"
				Date d = Date.parse(inputFormat, filter['date'].toString())
				between('date', d, d)
			}
			if (filter['description']) {
				ilike('description', "${filter['description']}%")
			}

			if (filter['pets']) {
				'in'('pet.id', filter['pets'].collect { (long) it })
			}
			if (filter['pet']) {
				eq('pet.id', (long) filter['pet'])
			}
		}
	}
}
