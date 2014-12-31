package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class DivisionCollectionSpec extends AbstractRestSpec {

	void "Test DivisionCollection crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def divisionCollectionId

		when: "Create divisionCollection"
		def response = restBuilder.post("${baseUrl}/divisioncollections") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		divisionCollectionId = response.json.id
		then: "Should create and return created values"
		
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read divisionCollection"
		response = restBuilder.get("${baseUrl}/divisioncollections/${divisionCollectionId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update divisionCollection"
		response = restBuilder.put("${baseUrl}/divisioncollections/${divisionCollectionId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get divisionCollection sorted list"
		response = restBuilder.get("${baseUrl}/divisioncollections.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == divisionCollectionId
		response.status == OK.value()

		
		when:"Find unexisting divisionCollection"
		response = restBuilder.get("${baseUrl}/divisioncollections/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete divisionCollection"
		response = restBuilder.delete("${baseUrl}/divisioncollections/${divisionCollectionId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
