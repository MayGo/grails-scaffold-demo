package test

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
class ClassifierSearchService {

	Classifier queryForClassifier(Long classifierId) {
		if (!classifierId || classifierId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Classifier classifier = Classifier.where { id == classifierId }.find()
		if (!classifier) {
			throw new ResourceNotFound("No Classifier found with Id :[$classifierId]")
		}
		return classifier
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Classifier.createCriteria()
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
					like('propertyname', searchString + '%')
					like('description', searchString + '%')
					like('constant', searchString + '%')
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
