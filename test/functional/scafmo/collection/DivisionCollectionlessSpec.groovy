package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class DivisionCollectionlessSpec extends AbstractRestSpec {

	void "Test DivisionCollectionless crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def divisionCollectionlessId

		when: "Create divisionCollectionless"
		def response = restBuilder.post("${baseUrl}/divisioncollectionlesss") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		divisionCollectionlessId = response.json.id
		then: "Should create and return created values"
		
			response.json.name == 'name'


		response.status == CREATED.value()

		when: "Read divisionCollectionless"
		response = restBuilder.get("${baseUrl}/divisioncollectionlesss/${divisionCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.name == 'name'


		response.status == OK.value()

		when: "Update divisionCollectionless"
		response = restBuilder.put("${baseUrl}/divisioncollectionlesss/${divisionCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				name = 'name'


			}
		}
		then: "Should return updated values"
			response.json.name == 'name'


		response.status == OK.value()


		when:"Get divisionCollectionless sorted list"
		response = restBuilder.get("${baseUrl}/divisioncollectionlesss.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == divisionCollectionlessId
		response.status == OK.value()

		
		when:"Find unexisting divisionCollectionless"
		response = restBuilder.get("${baseUrl}/divisioncollectionlesss/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete divisionCollectionless"
		response = restBuilder.delete("${baseUrl}/divisioncollectionlesss/${divisionCollectionlessId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
