package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class TaskSearchService {

	Task queryForRead(Long taskId) {
		return queryFor(taskId, true)
	}

	Task queryForWrite(Long taskId) {
		return queryFor(taskId, false)
	}

	private Task queryFor(Long taskId, boolean doReadOnly = true) {
		if (!taskId || taskId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Task task
		if (doReadOnly) {
			task = Task.read(taskId)
		} else {
			task = Task.get(taskId)
		}

		if (!task) {
			throw new ResourceNotFound("No Task found with Id :[$taskId]")
		}
		return task
	}

	PagedResultList search(TaskSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Task.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
			readOnly(doReadOnly)
		}

		return results
	}

	// TODO: Refactor and cleanup code so Codenarc check passes dynamic pgJsonHasFieldValue
	@SuppressWarnings(['AbcMetric', 'CyclomaticComplexity', 'MethodSize'])
	@GrailsCompileStatic(TypeCheckingMode.SKIP) // We want to use dynamically added criterias, eg: pgJsonHasFieldValue
	private void searchCriteria(BuildableCriteria builder, TaskSearchCommand cmd) {
		String searchString = cmd.searchString
		builder.with {
			if (cmd.id) {
				eq('id', cmd.id)
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
			if (cmd.dateCreated != null) {
				Date d = cmd.dateCreated
				between('dateCreated', d, d + 1)
			}
			if (cmd.deadline != null) {
				Date d = cmd.deadline
				between('deadline', d, d + 1)
			}
			if (cmd.details) {
				ilike('details', cmd.details + '%')
			}
//inList - status
			if (cmd.summary) {
				ilike('summary', cmd.summary + '%')
			}
			if (cmd.timeSpent != null) {
				eq('timeSpent', cmd.timeSpent)
			}
		}
	}
}
