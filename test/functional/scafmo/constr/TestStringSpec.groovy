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
				blankStr = 'Blank 681'
				creditCardStr = '372886934857774'
				emailStr = 'test682@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 683'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 684'
				urlStr = 'http://www.test685.com'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.blankStr == 'Blank 681'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test682@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 683'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 684'
			response.json.urlStr == 'http://www.test685.com'

			response.status == CREATED.value()
	}

	void "Test creating TestString instance."() {
		when: "Create testString"
			response = sendCreateWithData(){
				blankStr = 'Blank 686'
				creditCardStr = '372886934857774'
				emailStr = 'test687@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 688'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 689'
				urlStr = 'http://www.test690.com'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.blankStr == 'Blank 686'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test687@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 688'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 689'
			response.json.urlStr == 'http://www.test690.com'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading TestString instance."() {
		when: "Read testString"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.blankStr == 'Blank 686'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test687@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 688'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 689'
			response.json.urlStr == 'http://www.test690.com'

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
				blankStr = 'Blank 691'
				creditCardStr = '372886934857774'
				emailStr = 'test692@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 693'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 694'
				urlStr = 'http://www.test695.com'


			}
		then: "Should return updated values"
			response.json.blankStr == 'Blank 691'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test692@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 693'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 694'
			response.json.urlStr == 'http://www.test695.com'


			response.status == OK.value()
	}

	void "Test updating unexisting TestString instance."() {
		when: "Update unexisting testString"
			response = sendUpdateWithData("9999999999"){
					blankStr = 'Blank 691'
				creditCardStr = '372886934857774'
				emailStr = 'test692@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 693'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 694'
				urlStr = 'http://www.test695.com'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting testString id not a number"
			response = sendUpdateWithData("nonexistent"){
					blankStr = 'Blank 691'
				creditCardStr = '372886934857774'
				emailStr = 'test692@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 693'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 694'
				urlStr = 'http://www.test695.com'


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
