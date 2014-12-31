package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class RoleSpec extends AbstractRestSpec {

	void "Test Role crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def roleId

		when: "Create role"
		def response = restBuilder.post("${baseUrl}/roles") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				authority = 'authority'


			}
		}
		roleId = response.json.id
		then: "Should create and return created values"
		
			response.json.authority == 'authority'


		response.status == CREATED.value()

		when: "Read role"
		response = restBuilder.get("${baseUrl}/roles/${roleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.authority == 'authority'


		response.status == OK.value()

		when: "Update role"
		response = restBuilder.put("${baseUrl}/roles/${roleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				authority = 'authority'


			}
		}
		then: "Should return updated values"
			response.json.authority == 'authority'


		response.status == OK.value()


		when:"Get role sorted list"
		response = restBuilder.get("${baseUrl}/roles.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == roleId
		response.status == OK.value()

		
		when:"Find unexisting role"
		response = restBuilder.get("${baseUrl}/roles/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete role"
		response = restBuilder.delete("${baseUrl}/roles/${roleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
