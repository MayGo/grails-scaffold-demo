package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class PersonCollectionSpec extends AbstractRestSpec {

	void "Test PersonCollection crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def personCollectionId

		when: "Create personCollection"
		def response = restBuilder.post("${baseUrl}/personcollections") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				age = 0
				name = 'name'


			}
		}
		personCollectionId = response.json.id
		then: "Should create and return created values"
		
			response.json.age == 0
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read personCollection"
		response = restBuilder.get("${baseUrl}/personcollections/${personCollectionId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.age == 0
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update personCollection"
		response = restBuilder.put("${baseUrl}/personcollections/${personCollectionId}") {
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


		when:"Get personCollection sorted list"
		response = restBuilder.get("${baseUrl}/personcollections.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == personCollectionId
		response.status == OK.value()

		
		when:"Find unexisting personCollection"
		response = restBuilder.get("${baseUrl}/personcollections/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete personCollection"
		response = restBuilder.delete("${baseUrl}/personcollections/${personCollectionId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
