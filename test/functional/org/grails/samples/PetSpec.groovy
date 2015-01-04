package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class PetSpec extends AbstractRestSpec {

	void "Test Pet crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def petId

		when: "Create pet"
		def response = restBuilder.post("${baseUrl}/pets") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				birthDate = '2015-01-02 12:59:55.55+0200'
				name = 'name'


			}
		}
		petId = response.json.id
		then: "Should create and return created values"
		
			response.json.birthDate == '2015-01-02 12:59:55.55+0200'
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read pet"
		response = restBuilder.get("${baseUrl}/pets/${petId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.birthDate == '2015-01-02 12:59:55.55+0200'
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update pet"
		response = restBuilder.put("${baseUrl}/pets/${petId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				birthDate = '2015-01-02 12:59:55.55+0200'
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.birthDate == '2015-01-02 12:59:55.55+0200'
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get pet sorted list"
		response = restBuilder.get("${baseUrl}/pets.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == petId
		response.status == OK.value()

		
		when:"Find unexisting pet"
		response = restBuilder.get("${baseUrl}/pets/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete pet"
		response = restBuilder.delete("${baseUrl}/pets/${petId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
