package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class TestStringSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/teststrings"
	
	@Shared
	Long domainId
	@Shared
	Long otherDomainId
	
	@Shared
	def authResponse
	
	@Shared
	def response
	
	def setupSpec() {
		authResponse = sendCorrectCredentials()
	}
	
	void "Test creating another TestString instance."() {//This is for creating some data to test list sorting
		when: "Create testString"
			response = sendCreateWithData(){
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
			otherDomainId = response.json.id
			
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
	}

	void "Test creating TestString instance."() {
		when: "Create testString"
			response = sendCreateWithData(){
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
			domainId = response.json.id
			
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
	}
	
	
			
		

	void "Test reading TestString instance."() {
		when: "Read testString"
			response = readDomainItemWithParams(domainId.toString(), "")
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
	}
	
	
	void "Test excluding fields from reading TestString instance."() {
		when: "Read testString id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading TestString instance."() {
		when: "Read testString id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting TestString instance."() {
		when:"Find unexisting testString"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting testString id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating TestString instance."() {
		when: "Update testString"
			response = sendUpdateWithData(domainId.toString()){
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
	}

	void "Test updating unexisting TestString instance."() {
		when: "Update unexisting testString"
			response = sendUpdateWithData("9999999999"){
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
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting testString id not a number"
			response = sendUpdateWithData("nonexistent"){
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
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test TestString list sorting."() {
		when:"Get testString sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get testString sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test TestString list max property."() {
		when:"Get testString list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test TestString list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get testString list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get testString list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in TestString list."() {
		when:"Get testString sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in TestString list."() {
		when:"Get testString sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in TestString list."() {
		when:"Get testString sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other TestString instance."() {//This is for creating some data to test list sorting
		when: "Delete testString"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting TestString instance."() {
		when: "Delete testString"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
