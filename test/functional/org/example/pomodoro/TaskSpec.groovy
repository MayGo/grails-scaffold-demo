package org.example.pomodoro


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class TaskSpec extends AbstractRestSpec {

	void "Test Task crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def taskId

		when: "Create task"
		def response = restBuilder.post("${baseUrl}/tasks") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				deadline = '2015-01-02 12:59:55.55+0200'
				details = 'details'
				status = 'Open'
				summary = 'summary'
				timeSpent = 0


			}
		}
		taskId = response.json.id
		then: "Should create and return created values"
		
			response.json.deadline == '2015-01-02 12:59:55.55+0200'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'summary'
			response.json.timeSpent == 0


		response.status == CREATED.value()

		when: "Read task"
		response = restBuilder.get("${baseUrl}/tasks/${taskId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.deadline == '2015-01-02 12:59:55.55+0200'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'summary'
			response.json.timeSpent == 0


		response.status == OK.value()

		when: "Update task"
		response = restBuilder.put("${baseUrl}/tasks/${taskId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				deadline = '2015-01-02 12:59:55.55+0200'
				details = 'details'
				status = 'Open'
				summary = 'summary'
				timeSpent = 0


			}
		}
		then: "Should return updated values"
			response.json.deadline == '2015-01-02 12:59:55.55+0200'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'summary'
			response.json.timeSpent == 0


		response.status == OK.value()


		when:"Get task sorted list"
		response = restBuilder.get("${baseUrl}/tasks.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == taskId
		response.status == OK.value()

		
		when:"Find unexisting task"
		response = restBuilder.get("${baseUrl}/tasks/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete task"
		response = restBuilder.delete("${baseUrl}/tasks/${taskId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
