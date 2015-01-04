package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class SpecialitySpec extends AbstractRestSpec {

	void "Test Speciality crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def specialityId

		when: "Create speciality"
		def response = restBuilder.post("${baseUrl}/specialitys") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		specialityId = response.json.id
		then: "Should create and return created values"
		
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read speciality"
		response = restBuilder.get("${baseUrl}/specialitys/${specialityId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update speciality"
		response = restBuilder.put("${baseUrl}/specialitys/${specialityId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get speciality sorted list"
		response = restBuilder.get("${baseUrl}/specialitys.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == specialityId
		response.status == OK.value()

		
		when:"Find unexisting speciality"
		response = restBuilder.get("${baseUrl}/specialitys/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete speciality"
		response = restBuilder.delete("${baseUrl}/specialitys/${specialityId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
