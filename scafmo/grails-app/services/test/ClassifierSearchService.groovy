package test

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON
import grails.orm.HibernateCriteriaBuilder
import grails.orm.PagedResultList
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

//@GrailsCompileStatic
@Transactional(readOnly = true)
class ClassifierSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) Classifier.createCriteria()
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
					like('classname', searchString + '%')
					like('constant', searchString + '%')
					like('description', searchString + '%')
				}
			}
			if (filter['classname']) {
				ilike('classname', "${filter['classname']}%")
			}
			if (filter['constant']) {
				ilike('constant', "${filter['constant']}%")
			}
			if (filter['description']) {
				ilike('description', "${filter['description']}%")
			}
			if (filter['propertyname']) {
				ilike('propertyname', "${filter['propertyname']}%")
			}
		}
	}
}
