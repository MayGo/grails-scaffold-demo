package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class VetSpec extends AbstractRestSpec {

	void "Test Vet crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def vetId

		when: "Create vet"
		def response = restBuilder.post("${baseUrl}/vets") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				firstName = 'firstName'
				lastName = 'lastName'


			}
		}
		vetId = response.json.id
		then: "Should create and return created values"
		
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


		response.status == CREATED.value()

		when: "Read vet"
		response = restBuilder.get("${baseUrl}/vets/${vetId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


		response.status == OK.value()

		when: "Update vet"
		response = restBuilder.put("${baseUrl}/vets/${vetId}") {
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


		when:"Get vet sorted list"
		response = restBuilder.get("${baseUrl}/vets.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == vetId
		response.status == OK.value()

		
		when:"Find unexisting vet"
		response = restBuilder.get("${baseUrl}/vets/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete vet"
		response = restBuilder.delete("${baseUrl}/vets/${vetId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
