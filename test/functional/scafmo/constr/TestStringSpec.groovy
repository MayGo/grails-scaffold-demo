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
				blankStr = 'Blank 506'
				creditCardStr = '372886934857774'
				emailStr = 'test507@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 508'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 509'
				urlStr = 'http://www.test510.com'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.blankStr == 'Blank 506'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test507@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 508'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 509'
			response.json.urlStr == 'http://www.test510.com'

			response.status == CREATED.value()
	}

	void "Test creating TestString instance."() {
		when: "Create testString"
			response = sendCreateWithData(){
				blankStr = 'Blank 511'
				creditCardStr = '372886934857774'
				emailStr = 'test512@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 513'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 514'
				urlStr = 'http://www.test515.com'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.blankStr == 'Blank 511'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test512@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 513'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 514'
			response.json.urlStr == 'http://www.test515.com'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading TestString instance."() {
		when: "Read testString"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.blankStr == 'Blank 511'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test512@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 513'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 514'
			response.json.urlStr == 'http://www.test515.com'

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
				blankStr = 'Blank 516'
				creditCardStr = '372886934857774'
				emailStr = 'test517@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 518'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 519'
				urlStr = 'http://www.test520.com'


			}
		then: "Should return updated values"
			response.json.blankStr == 'Blank 516'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test517@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 518'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'U 519'
			response.json.urlStr == 'http://www.test520.com'


			response.status == OK.value()
	}

	void "Test updating unexisting TestString instance."() {
		when: "Update unexisting testString"
			response = sendUpdateWithData("9999999999"){
					blankStr = 'Blank 516'
				creditCardStr = '372886934857774'
				emailStr = 'test517@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 518'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 519'
				urlStr = 'http://www.test520.com'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting testString id not a number"
			response = sendUpdateWithData("nonexistent"){
					blankStr = 'Blank 516'
				creditCardStr = '372886934857774'
				emailStr = 'test517@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 518'
				sizeStr = 'sizeStr'
				uniqueStr = 'U 519'
				urlStr = 'http://www.test520.com'


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
	
	
	 // have to have more then maxLimit items
	void "Test TestString list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get testString list without max param"
			response = queryListWithParams("")

		then:"Should return default maximum items"
			response.json.size() == 10
			
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
	
	void "Test filtering in TestString list by id."() {
		when:"Get testString list filtered by id"

			response = queryListWithUrlVariables("filter={filter}", [filter:"{id:${domainId}}"])

		then:"Should contains one item, just inserted item."
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == OK.value()
	}
	
	void "Test filtering in TestString list by all properties."() {
		given:
			response = queryListWithUrlVariables("filter={filter}", [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			"{}"                || 10
	
		//Can't predict 'size'	"""{"blankStr":"Blank 516"}"""     		|| 1

	
			"""{"creditCardStr":"372886934857774"}"""     		|| 10

	
			"""{"emailStr":"test517@test.com"}"""     		|| 1

	
			"""{"inListStr":"test1"}"""     		|| 10

	
			"""{"matchesStr":"ABC"}"""     		|| 10

	
			"""{"maxSizeStr":"ABCDE"}"""     		|| 10

	
			"""{"minSizeStr":"ABC"}"""     		|| 10

	
		//Can't predict 'size'	"""{"notEqualStr":"notEqualStr 518"}"""     		|| 1

	
			"""{"sizeStr":"sizeStr"}"""     		|| 10

	
		//Can't predict 'size'	"""{"uniqueStr":"U 519"}"""     		|| 1

	
			"""{"urlStr":"http://www.test520.com"}"""     		|| 1

	
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
