package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class PetTypeSpec extends AbstractRestSpec {

	void "Test PetType crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def petTypeId

		when: "Create petType"
		def response = restBuilder.post("${baseUrl}/pettypes") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		petTypeId = response.json.id
		then: "Should create and return created values"
		
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read petType"
		response = restBuilder.get("${baseUrl}/pettypes/${petTypeId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update petType"
		response = restBuilder.put("${baseUrl}/pettypes/${petTypeId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get petType sorted list"
		response = restBuilder.get("${baseUrl}/pettypes.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == petTypeId
		response.status == OK.value()

		
		when:"Find unexisting petType"
		response = restBuilder.get("${baseUrl}/pettypes/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete petType"
		response = restBuilder.delete("${baseUrl}/pettypes/${petTypeId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
