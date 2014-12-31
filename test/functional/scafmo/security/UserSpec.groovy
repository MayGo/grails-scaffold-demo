package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class UserSpec extends AbstractRestSpec {

	void "Test User crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def userId

		when: "Create user"
		def response = restBuilder.post("${baseUrl}/users") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				accountExpired = false
				accountLocked = false
				enabled = true
				passwordExpired = false
				username = 'username'


			}
		}
		userId = response.json.id
		then: "Should create and return created values"
		
			response.json.accountExpired == false
			response.json.accountLocked == false
			response.json.enabled == true
			response.json.passwordExpired == false
			response.json.username == 'username'


		response.status == CREATED.value()

		when: "Read user"
		response = restBuilder.get("${baseUrl}/users/${userId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.accountExpired == false
			response.json.accountLocked == false
			response.json.enabled == true
			response.json.passwordExpired == false
			response.json.username == 'username'


		response.status == OK.value()

		when: "Update user"
		response = restBuilder.put("${baseUrl}/users/${userId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				accountExpired = false
				accountLocked = false
				enabled = true
				passwordExpired = false
				username = 'username'


			}
		}
		then: "Should return updated values"
			response.json.accountExpired == false
			response.json.accountLocked == false
			response.json.enabled == true
			response.json.passwordExpired == false
			response.json.username == 'username'


		response.status == OK.value()


		when:"Get user sorted list"
		response = restBuilder.get("${baseUrl}/users.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == userId
		response.status == OK.value()

		
		when:"Find unexisting user"
		response = restBuilder.get("${baseUrl}/users/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete user"
		response = restBuilder.delete("${baseUrl}/users/${userId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
