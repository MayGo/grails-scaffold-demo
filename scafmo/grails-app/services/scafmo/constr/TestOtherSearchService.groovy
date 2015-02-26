package scafmo.constr

import grails.compiler.GrailsCompileStatic
import grails.converters.JSON
import grails.orm.HibernateCriteriaBuilder
import grails.orm.PagedResultList
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

//@GrailsCompileStatic
@Transactional(readOnly = true)
class TestOtherSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) TestOther.createCriteria()
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

					if(searchString.equalsIgnoreCase("false") || searchString.equalsIgnoreCase("true")) {
						eq('booleanNullable', searchString.toBoolean())
					}
					
					// no type defined for testDate 
					// no type defined for testEnum 
				}
			}
			if (filter['booleanNullable']) {
				eq('booleanNullable', filter['booleanNullable'].toString().toBoolean())
			}

			if (filter['testDate']) {
				String inputFormat = "yyyy-MM-dd HH:mm:ss.SSSZ"
				Date d = Date.parse(inputFormat, filter['testDate'].toString())
				between('testDate', d, d)
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
