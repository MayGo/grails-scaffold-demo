package scafmo.collection

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.datastore.mapping.query.api.BuildableCriteria


//@GrailsCompileStatic
@Transactional(readOnly = true)
class PersonCollectionSearchService {

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) PersonCollection.createCriteria()
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

					if (searchString.isInteger()) {
						eq('age', searchString.toInteger())
					}
				}
			}
			if (filter['age']) {
				eq('age', filter['age'].toString().toInteger())
			}
			if (filter['name']) {
				ilike('name', "${filter['name']}%")
			}

			if (filter['divisions']) {
				'in'('division.id', filter['divisions'].collect { (long) it })
			}
			if (filter['division']) {
				eq('division.id', (long) filter['division'])
			}
		}
	}
}