package org.example.pomodoro

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
class TaskSearchService {

	Task queryForTask(Long taskId) {
		if (!taskId || taskId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Task task = Task.where { id == taskId }.find()
		if (!task) {
			throw new ResourceNotFound("No Task found with Id :[$taskId]")
		}
		return task
	}

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

					if (searchString.isLong()) {
						eq('timeSpent', searchString.toLong())
					}
					like('summary', searchString + '%')
					like('status', searchString + '%')
				}
			}

			if (filter['dateCreated']) {
				Date d
				if (filter['dateCreated'].toString().isNumber()) {
					d = new Date(filter['dateCreated'].toString().toLong())
				} else {
					String inputFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
					d = Date.parse(inputFormat, filter['dateCreated'].toString())
				}

				between('dateCreated', d, d + 1)
			}

			if (filter['deadline']) {
				Date d
				if (filter['deadline'].toString().isNumber()) {
					d = new Date(filter['deadline'].toString().toLong())
				} else {
					String inputFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
					d = Date.parse(inputFormat, filter['deadline'].toString())
				}

				between('deadline', d, d + 1)
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
