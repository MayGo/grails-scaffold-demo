package scafmo.constr

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
class TestOtherSearchService {

	TestOther queryForTestOther(Long testOtherId) {
		if (!testOtherId || testOtherId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestOther testOther = TestOther.where { id == testOtherId }.find()
		if (!testOther) {
			throw new ResourceNotFound("No TestOther found with Id :[$testOtherId]")
		}
		return testOther
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) TestOther.createCriteria()
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
					// no type defined for testEnum 
					// no type defined for testDate 

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('booleanNullable', searchString.toBoolean())
					}
				}
			}
			if (filter['booleanNullable']) {
				eq('booleanNullable', filter['booleanNullable'].toString().toBoolean())
			}

			if (filter['testDate']) {
				Date d
				if (filter['testDate'].toString().isNumber()) {
					d = new Date(filter['testDate'].toString().toLong())
				} else {
					String inputFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
					d = Date.parse(inputFormat, filter['testDate'].toString())
				}

				between('testDate', d, d + 1)
			}
			if (filter['testEnum']) {
				//enum
			}

			if (filter['testStringTypes']) {
				'in'('testStringType.id', filter['testStringTypes'].collect { (long) it })
			}
			if (filter['testStringType']) {
				eq('testStringType.id', (long) filter['testStringType'])
			}
		}
	}
}
