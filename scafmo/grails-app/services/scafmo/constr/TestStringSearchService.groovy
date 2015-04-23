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
class TestStringSearchService {

	TestString queryForTestString(Long testStringId) {
		if (!testStringId || testStringId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestString testString = TestString.where { id == testStringId }.find()
		if (!testString) {
			throw new ResourceNotFound("No TestString found with Id :[$testStringId]")
		}
		return testString
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) TestString.createCriteria()
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
					like('urlStr', searchString + '%')
					like('uniqueStr', searchString + '%')
					like('sizeStr', searchString + '%')
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
