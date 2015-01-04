package org.example.pomodoro


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class TagSpec extends AbstractRestSpec {

	void "Test Tag crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def tagId

		when: "Create tag"
		def response = restBuilder.post("${baseUrl}/tags") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		tagId = response.json.id
		then: "Should create and return created values"
		
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read tag"
		response = restBuilder.get("${baseUrl}/tags/${tagId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update tag"
		response = restBuilder.put("${baseUrl}/tags/${tagId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get tag sorted list"
		response = restBuilder.get("${baseUrl}/tags.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == tagId
		response.status == OK.value()

		
		when:"Find unexisting tag"
		response = restBuilder.get("${baseUrl}/tags/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete tag"
		response = restBuilder.delete("${baseUrl}/tags/${tagId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
