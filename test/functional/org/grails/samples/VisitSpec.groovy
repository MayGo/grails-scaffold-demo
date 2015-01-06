package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class VisitSpec extends AbstractRestSpec {

	void "Test Visit crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def visitId

		when: "Create visit"
		def response = restBuilder.post("${baseUrl}/visits") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				date = '2015-01-02 12:59:56.56+0200'
				description = 'description'


			}
		}
		visitId = response.json.id
		then: "Should create and return created values"
		
			response.json.date == '2015-01-02 12:59:56.56+0200'
			response.json.description == 'description'


		response.status == CREATED.value()

		when: "Read visit"
		response = restBuilder.get("${baseUrl}/visits/${visitId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.date == '2015-01-02 12:59:56.56+0200'
			response.json.description == 'description'


		response.status == OK.value()

		when: "Update visit"
		response = restBuilder.put("${baseUrl}/visits/${visitId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				date = '2015-01-02 12:59:56.56+0200'
				description = 'description'


			}
		}
		then: "Should return updated values"
			response.json.date == '2015-01-02 12:59:56.56+0200'
			response.json.description == 'description'


		response.status == OK.value()


		when:"Get visit sorted list"
		response = restBuilder.get("${baseUrl}/visits.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == visitId
		response.status == OK.value()

		
		when:"Find unexisting visit"
		response = restBuilder.get("${baseUrl}/visits/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete visit"
		response = restBuilder.delete("${baseUrl}/visits/${visitId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
