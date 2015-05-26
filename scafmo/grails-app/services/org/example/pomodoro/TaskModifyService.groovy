package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class TaskModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Task createTask(Map data) {
		Task task = Task.newInstance()
		return createOrUpdate(task, data)
	}

	Task updateTask(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Task task = Task.where { id == objId }.find()

		if (!task) {
			throw new ResourceNotFound("No Task found with Id :[$objId]")
		}

		return createOrUpdate(task, data)
	}

	Task createOrUpdate(Task task, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind task, data as SimpleMapDataBindingSource

		task.save failOnError: true

		return task
	}

	void deleteTask(Long taskId) {
		if (!taskId || taskId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Task task = Task.where { id == taskId }.find()

		if (!task) {
			throw new ResourceNotFound("No Task found with Id:$taskId")
		}

		task.delete()
	}
}

