package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(TaskSearchService)
@Mock(Task)
class TaskSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Task without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Task with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Task with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Task with valid id returns Task instance'() {

		setup:
			Long taskId = createValidTask().id
		when:
			Task task = service.queryForRead(taskId)
		then:
			task != null
			task.id == 1
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

