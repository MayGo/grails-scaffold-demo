package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TaskModifyService {
	def grailsWebDataBinder

	Task createTask(Map data){
		Task task = Task.newInstance()
		return createOrUpdate(task, data)
	}

	Task updateTask(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Task task = queryForTask(data.id)

		if(!task){
			throw new ResourceNotFound("No Task found with Id :[${data.id}]")
		}

		return createOrUpdate(task, data)
	}

	Task createOrUpdate(Task task, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind task, data as SimpleMapDataBindingSource

		task.save flush: true, failOnError: true

		return task
	}

	Task queryForTask(long id){
		return Task.get(id)
	}
}
