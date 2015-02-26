package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.datastore.mapping.query.api.BuildableCriteria


//@GrailsCompileStatic
@Transactional(readOnly = true)
class TaskSearchService {

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Task.createCriteria()
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
					like('details', searchString + '%')
					like('status', searchString + '%')
					like('summary', searchString + '%')
				}
			}

			if (filter['deadline']) {
				String inputFormat = "yyyy-MM-dd HH:mm:ss.SSSZ"
				Date d = Date.parse(inputFormat, filter['deadline'].toString())
				between('deadline', d, d)
			}
			if (filter['details']) {
				ilike('details', "${filter['details']}%")
			}
			if (filter['status']) {
				//inList
			}
			if (filter['summary']) {
				ilike('summary', "${filter['summary']}%")
			}
			if (filter['timeSpent']) {
				eq('timeSpent', filter['timeSpent'].toString().toLong())
			}
		}
	}
}
