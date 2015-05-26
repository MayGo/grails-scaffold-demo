package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TaskModifyService)
@Mock(Task)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TaskModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Task with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createTask(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Task with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createTask(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Task with valid data returns Task instance'() {
		setup:
			Map data = validData()
		when:
			Task task = service.createTask(data)
		then:
			task != null
			task.id != null
	}

	void 'Updating Task without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateTask(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Task with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateTask(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Task with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateTask(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Task with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTask().id
		when:
			service.updateTask(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Task with valid data returns Task instance'() {

		setup:
			Map data = validData()
			data.id = createValidTask().id
		when:
			Task task = service.updateTask(data)
		then:
			task != null
			task.id == 1
	}

	void 'Deleting Task without id is not possible'() {
		when:
			service.deleteTask(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Task with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteTask(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Task with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteTask(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Task is possible'() {
		setup:
			Long taskId = createValidTask().id
			Task task = Task.findById(taskId).find()
		when:
			service.deleteTask(taskId)
		then:
			task != null
			Task.findById(taskId) == null
	}

	Map invalidData() {

		return ['details': null,
 'status': null,
 'summary': null,
 'timeSpent': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'dateCreated':  new Date().clearTime(),
  'deadline':  new Date().clearTime(),
  'details':  'details',
  'status':  'Open',
  'summary':  'Work Summary 152'
]
		return data
	}

	Task createValidTask() {
		Task task = new Task(validData())
		task.save flush: true, failOnError: true
		return task
	}

}

