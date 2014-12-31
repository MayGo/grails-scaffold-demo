package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class TestNumberSpec extends AbstractRestSpec {

	void "Test TestNumber crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def testNumberId

		when: "Create testNumber"
		def response = restBuilder.post("${baseUrl}/testnumbers") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0


			}
		}
		testNumberId = response.json.id
		then: "Should create and return created values"
		
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0


		response.status == CREATED.value()

		when: "Read testNumber"
		response = restBuilder.get("${baseUrl}/testnumbers/${testNumberId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0


		response.status == OK.value()

		when: "Update testNumber"
		response = restBuilder.put("${baseUrl}/testnumbers/${testNumberId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0


			}
		}
		then: "Should return updated values"
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0


		response.status == OK.value()


		when:"Get testNumber sorted list"
		response = restBuilder.get("${baseUrl}/testnumbers.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == testNumberId
		response.status == OK.value()

		
		when:"Find unexisting testNumber"
		response = restBuilder.get("${baseUrl}/testnumbers/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete testNumber"
		response = restBuilder.delete("${baseUrl}/testnumbers/${testNumberId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
