package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class PersonSpec extends AbstractRestSpec {

	void "Test Person crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def personId

		when: "Create person"
		def response = restBuilder.post("${baseUrl}/persons") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				firstName = 'firstName'
				lastName = 'lastName'


			}
		}
		personId = response.json.id
		then: "Should create and return created values"
		
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


		response.status == CREATED.value()

		when: "Read person"
		response = restBuilder.get("${baseUrl}/persons/${personId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


		response.status == OK.value()

		when: "Update person"
		response = restBuilder.put("${baseUrl}/persons/${personId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				firstName = 'firstName'
				lastName = 'lastName'


			}
		}
		then: "Should return updated values"
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


		response.status == OK.value()


		when:"Get person sorted list"
		response = restBuilder.get("${baseUrl}/persons.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == personId
		response.status == OK.value()

		
		when:"Find unexisting person"
		response = restBuilder.get("${baseUrl}/persons/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete person"
		response = restBuilder.delete("${baseUrl}/persons/${personId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
