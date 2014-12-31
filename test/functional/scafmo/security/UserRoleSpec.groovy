package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class UserRoleSpec extends AbstractRestSpec {

	void "Test UserRole crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def userRoleId

		when: "Create userRole"
		def response = restBuilder.post("${baseUrl}/userroles") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {


			}
		}
		userRoleId = response.json.id
		then: "Should create and return created values"
		


		response.status == CREATED.value()

		when: "Read userRole"
		response = restBuilder.get("${baseUrl}/userroles/${userRoleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"


		response.status == OK.value()

		when: "Update userRole"
		response = restBuilder.put("${baseUrl}/userroles/${userRoleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {


			}
		}
		then: "Should return updated values"


		response.status == OK.value()


		when:"Get userRole sorted list"
		response = restBuilder.get("${baseUrl}/userroles.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == userRoleId
		response.status == OK.value()

		
		when:"Find unexisting userRole"
		response = restBuilder.get("${baseUrl}/userroles/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete userRole"
		response = restBuilder.delete("${baseUrl}/userroles/${userRoleId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
