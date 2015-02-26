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
class TestNumberSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) TestNumber.createCriteria()
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

					if(searchString.isDouble()) {
						eq('doubleNr', searchString.toDouble())
					}

					if(searchString.isFloat()) {
						eq('floatNr', searchString.toFloat())
					}

					if(searchString.isFloat()) {
						eq('floatNrScale', searchString.toFloat())
					}
				}
			}
			if (filter['doubleNr']) {
				eq('doubleNr', filter['doubleNr'].toString().toDouble())
			}
			if (filter['floatNr']) {
				eq('floatNr', filter['floatNr'].toString().toFloat())
			}
			if (filter['floatNrScale']) {
				eq('floatNrScale', filter['floatNrScale'].toString().toFloat())
			}
			if (filter['integerNr']) {
				eq('integerNr', filter['integerNr'].toString().toInteger())
			}
			if (filter['integerNrInList']) {
				//inList
			}
			if (filter['integerNrMax']) {
				eq('integerNrMax', filter['integerNrMax'].toString().toInteger())
			}
			if (filter['integerNrMin']) {
				eq('integerNrMin', filter['integerNrMin'].toString().toInteger())
			}
			if (filter['integerNrNotEqual']) {
				eq('integerNrNotEqual', filter['integerNrNotEqual'].toString().toInteger())
			}
			if (filter['integerNrRange']) {
				eq('integerNrRange', filter['integerNrRange'].toString().toInteger())
			}
			if (filter['integerNrUnique']) {
				eq('integerNrUnique', filter['integerNrUnique'].toString().toInteger())
			}
			if (filter['longNr']) {
				eq('longNr', filter['longNr'].toString().toLong())
			}
		}
	}
}
