package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class TestOtherSpec extends AbstractRestSpec {

	void "Test TestOther crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def testOtherId

		when: "Create testOther"
		def response = restBuilder.post("${baseUrl}/testothers") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				booleanNullable = 'false'
				testDate = '2015-01-02 12:59:57.57+0200'
				testEnum = 'TEST_1'


			}
		}
		testOtherId = response.json.id
		then: "Should create and return created values"
		
			response.json.booleanNullable == 'false'
			response.json.testDate == '2015-01-02 12:59:57.57+0200'
			response.json.testEnum == 'TEST_1'


		response.status == CREATED.value()

		when: "Read testOther"
		response = restBuilder.get("${baseUrl}/testothers/${testOtherId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.booleanNullable == 'false'
			response.json.testDate == '2015-01-02 12:59:57.57+0200'
			response.json.testEnum == 'TEST_1'


		response.status == OK.value()

		when: "Update testOther"
		response = restBuilder.put("${baseUrl}/testothers/${testOtherId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				booleanNullable = 'false'
				testDate = '2015-01-02 12:59:57.57+0200'
				testEnum = 'TEST_1'


			}
		}
		then: "Should return updated values"
			response.json.booleanNullable == 'false'
			response.json.testDate == '2015-01-02 12:59:57.57+0200'
			response.json.testEnum == 'TEST_1'


		response.status == OK.value()


		when:"Get testOther sorted list"
		response = restBuilder.get("${baseUrl}/testothers.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == testOtherId
		response.status == OK.value()

		
		when:"Find unexisting testOther"
		response = restBuilder.get("${baseUrl}/testothers/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete testOther"
		response = restBuilder.delete("${baseUrl}/testothers/${testOtherId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
