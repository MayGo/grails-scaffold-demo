package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TaskModifyService)
@Mock(Task)
class TaskModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateTask(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Task with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateTask(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Task with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTask().id
		when:
			Task task = service.updateTask(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"dateCreated":"2015-02-26T12:18:30Z","deadline":"2015-02-25T22:00:00Z","details":"details","status":"Open","summary":"Work Summary 152","timeSpent":0]

		return data
	}

	Task createValidTask(){
		Task task = new Task(validData())
		task.save flush:true, failOnError: true
		return task
	}

}

