package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class PersonCollectionlessSpec extends AbstractRestSpec {

	void "Test PersonCollectionless crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def personCollectionlessId

		when: "Create personCollectionless"
		def response = restBuilder.post("${baseUrl}/personcollectionlesss") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				age = 0
				name = 'name'


			}
		}
		personCollectionlessId = response.json.id
		then: "Should create and return created values"
		
			response.json.age == 0
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read personCollectionless"
		response = restBuilder.get("${baseUrl}/personcollectionlesss/${personCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.age == 0
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update personCollectionless"
		response = restBuilder.put("${baseUrl}/personcollectionlesss/${personCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				age = 0
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.age == 0
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get personCollectionless sorted list"
		response = restBuilder.get("${baseUrl}/personcollectionlesss.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == personCollectionlessId
		response.status == OK.value()

		
		when:"Find unexisting personCollectionless"
		response = restBuilder.get("${baseUrl}/personcollectionlesss/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete personCollectionless"
		response = restBuilder.delete("${baseUrl}/personcollectionlesss/${personCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
