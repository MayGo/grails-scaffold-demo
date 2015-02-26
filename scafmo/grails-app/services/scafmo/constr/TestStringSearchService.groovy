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
class TestStringSearchService {

	PagedResultList search(Map params) {

		HibernateCriteriaBuilder criteriaBuilder = (HibernateCriteriaBuilder) TestString.createCriteria()
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
					like('blankStr', searchString + '%')
					like('creditCardStr', searchString + '%')
					like('emailStr', searchString + '%')
				}
			}
			if (filter['blankStr']) {
				ilike('blankStr', "${filter['blankStr']}%")
			}
			if (filter['creditCardStr']) {
				ilike('creditCardStr', "${filter['creditCardStr']}%")
			}
			if (filter['emailStr']) {
				ilike('emailStr', "${filter['emailStr']}%")
			}
			if (filter['inListStr']) {
				//inList
			}
			if (filter['matchesStr']) {
				ilike('matchesStr', "${filter['matchesStr']}%")
			}
			if (filter['maxSizeStr']) {
				ilike('maxSizeStr', "${filter['maxSizeStr']}%")
			}
			if (filter['minSizeStr']) {
				ilike('minSizeStr', "${filter['minSizeStr']}%")
			}
			if (filter['notEqualStr']) {
				ilike('notEqualStr', "${filter['notEqualStr']}%")
			}
			if (filter['sizeStr']) {
				ilike('sizeStr', "${filter['sizeStr']}%")
			}
			if (filter['uniqueStr']) {
				ilike('uniqueStr', "${filter['uniqueStr']}%")
			}
			if (filter['urlStr']) {
				ilike('urlStr', "${filter['urlStr']}%")
			}
		}
	}
}
