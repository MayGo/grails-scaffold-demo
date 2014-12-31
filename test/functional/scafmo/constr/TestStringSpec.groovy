package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec




class TestStringSpec extends AbstractRestSpec {

	void "Test TestString crud"() {

		given:
		def authResponse = sendCorrectCredentials()
		def testStringId

		when: "Create testString"
		def response = restBuilder.post("${baseUrl}/teststrings") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				blankStr = 'blankStr'
				creditCardStr = '378282246310005'
				emailStr = 'a@b.com'
				inListStr = 'test1'
				matchesStr = 'matchesStr'
				maxSizeStr = 'maxSi'
				minSizeStr = 'minSizeStr'
				notEqualStr = 'notEqualStr'
				sizeStr = 'sizeStr'
				uniqueStr = 'uniqueStr'
				urlStr = 'http://www.example.com'


			}
		}
		testStringId = response.json.id
		then: "Should create and return created values"
		
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr'
			response.json.urlStr == 'http://www.example.com'


		response.status == CREATED.value()

		when: "Read testString"
		response = restBuilder.get("${baseUrl}/teststrings/${testStringId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then: "Should return correct values"
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr'
			response.json.urlStr == 'http://www.example.com'


		response.status == OK.value()

		when: "Update testString"
		response = restBuilder.put("${baseUrl}/teststrings/${testStringId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
			json {
				blankStr = 'blankStr'
				creditCardStr = '378282246310005'
				emailStr = 'a@b.com'
				inListStr = 'test1'
				matchesStr = 'matchesStr'
				maxSizeStr = 'maxSi'
				minSizeStr = 'minSizeStr'
				notEqualStr = 'notEqualStr'
				sizeStr = 'sizeStr'
				uniqueStr = 'uniqueStr'
				urlStr = 'http://www.example.com'


			}
		}
		then: "Should return updated values"
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr'
			response.json.urlStr == 'http://www.example.com'


		response.status == OK.value()


		when:"Get testString sorted list"
		response = restBuilder.get("${baseUrl}/teststrings.json?order=desc&sort=id") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}

		then:"First item should be just inserted object"
		response.json[0].id == testStringId
		response.status == OK.value()

		
		when:"Find unexisting testString"
		response = restBuilder.get("${baseUrl}/teststrings/nonexistent") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:"Should not find"
		response.status == NOT_FOUND.value()

		
		when: "Delete testString"
		response = restBuilder.delete("${baseUrl}/teststrings/${testStringId}") {
			header 'Authorization', 'Bearer '+authResponse.json.access_token
			accept "application/json"
		}
		then:
		response.status == NO_CONTENT.value()
		
	}
}
