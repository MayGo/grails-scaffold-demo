package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class OwnerSpec extends AbstractRestSpec {

	void "Test Owner crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def ownerId

		when: "Create owner"
		def response = restBuilder.post("${baseUrl}/owners") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = 'telephone'


			}
		}
		ownerId = response.json.id
		then: "Should create and return created values"
		
			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == 'telephone'


		response.status == CREATED.value()

		when: "Read owner"
		response = restBuilder.get("${baseUrl}/owners/${ownerId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == 'telephone'


		response.status == OK.value()

		when: "Update owner"
		response = restBuilder.put("${baseUrl}/owners/${ownerId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = 'telephone'


			}
		}
		then: "Should return updated values"
			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == 'telephone'


		response.status == OK.value()


		when:"Get owner sorted list"
		response = restBuilder.get("${baseUrl}/owners.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == ownerId
		response.status == OK.value()

		
		when:"Find unexisting owner"
		response = restBuilder.get("${baseUrl}/owners/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete owner"
		response = restBuilder.delete("${baseUrl}/owners/${ownerId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
