package scafmo.constr


import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class TestStringSpec extends RestQueries implements TestUtils{

	@Shared
	Long domainId

	@Shared
	Long otherDomainId

	@Shared
	def authResponse

	@Shared
	def response

	def setupSpec() {
		restBuilder = new RestBuilder()
		authResponse = sendCorrectCredentials(APP_URL)
		// Initialize RestQueries static variables
		ACCESS_TOKEN = authResponse.json.access_token
		REST_URL = "${APP_URL}/teststrings/v1"
	}

	void 'Test creating another TestString instance.'() {//This is for creating some data to test list sorting
		when: 'Create testString'
			response = sendCreateWithData(){
				blankStr = 'Blank 751'
				creditCardStr = '372886934857774'
				emailStr = 'test752@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 753'
				sizeStr = 'sizeStr'
				textareaStr = 'textareaStr'
				uniqueStr = 'U 754'
				urlStr = 'http://www.test755.com'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.blankStr == 'Blank 751'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test752@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 753'
			response.json.sizeStr == 'sizeStr'
			response.json.textareaStr == 'textareaStr'
			response.json.uniqueStr == 'U 754'
			response.json.urlStr == 'http://www.test755.com'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating TestString instance.'() {
		when: 'Create testString'
			response = sendCreateWithData(){
				blankStr = 'Blank 756'
				creditCardStr = '372886934857774'
				emailStr = 'test757@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 758'
				sizeStr = 'sizeStr'
				textareaStr = 'textareaStr'
				uniqueStr = 'U 759'
				urlStr = 'http://www.test760.com'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.blankStr == 'Blank 756'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test757@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 758'
			response.json.sizeStr == 'sizeStr'
			response.json.textareaStr == 'textareaStr'
			response.json.uniqueStr == 'U 759'
			response.json.urlStr == 'http://www.test760.com'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading TestString instance.'() {
		when: 'Read testString'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.blankStr == 'Blank 756'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test757@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 758'
			response.json.sizeStr == 'sizeStr'
			response.json.textareaStr == 'textareaStr'
			response.json.uniqueStr == 'U 759'
			response.json.urlStr == 'http://www.test760.com'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading TestString instance.'() {
		when: 'Read testString id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading TestString instance.'() {
		when: 'Read testString id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting TestString instance.'() {
		when: 'Find unexisting testString'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting testString id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating TestString instance.'() {
		when: 'Update testString'
			response = sendUpdateWithData(domainId.toString()){
				blankStr = 'Blank 761'
				creditCardStr = '372886934857774'
				emailStr = 'test762@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 763'
				sizeStr = 'sizeStr'
				textareaStr = 'textareaStr'
				uniqueStr = 'U 764'
				urlStr = 'http://www.test765.com'

			}
		then: 'Should return updated values'
			response.json.blankStr == 'Blank 761'
			response.json.creditCardStr == '372886934857774'
			response.json.emailStr == 'test762@test.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'ABC'
			response.json.maxSizeStr == 'ABCDE'
			response.json.minSizeStr == 'ABC'
			response.json.notEqualStr == 'notEqualStr 763'
			response.json.sizeStr == 'sizeStr'
			response.json.textareaStr == 'textareaStr'
			response.json.uniqueStr == 'U 764'
			response.json.urlStr == 'http://www.test765.com'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting TestString instance.'() {
		when: 'Update unexisting testString'
			response = sendUpdateWithData('9999999999'){
					blankStr = 'Blank 761'
				creditCardStr = '372886934857774'
				emailStr = 'test762@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 763'
				sizeStr = 'sizeStr'
				textareaStr = 'textareaStr'
				uniqueStr = 'U 764'
				urlStr = 'http://www.test765.com'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting testString id not a number'
			response = sendUpdateWithData('nonexistent'){
					blankStr = 'Blank 761'
				creditCardStr = '372886934857774'
				emailStr = 'test762@test.com'
				inListStr = 'test1'
				matchesStr = 'ABC'
				maxSizeStr = 'ABCDE'
				minSizeStr = 'ABC'
				notEqualStr = 'notEqualStr 763'
				sizeStr = 'sizeStr'
				textareaStr = 'textareaStr'
				uniqueStr = 'U 764'
				urlStr = 'http://www.test765.com'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test TestString list sorting.'() {
		when: 'Get testString sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get testString sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test TestString list max property query 2 items.'() {
		when: 'Get testString list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using TestString list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get testString list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get testString list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get testString list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in TestString list.'() {
		when: 'Get testString sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in TestString list.'() {
		when: 'Get testString sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in TestString list by dummy searchString.'() {
		when: 'Get testString list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in TestString list by real searchString.'() {
		when: 'Get testString list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "http://www.test765.com"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in TestString list by id.'() {
		when: 'Get testString list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("TestString list search with props '#filter' returns '#respSize' items")
	void 'Filtering in TestString list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
//Can't predict 'size'			[blankStr:'Blank 761'] || 1 
			[creditCardStr:'372886934857774'] || 10 
			[emailStr:'test762@test.com'] || 1 
			[inListStr:'test1'] || 10 
			[matchesStr:'ABC'] || 10 
			[maxSizeStr:'ABCDE'] || 10 
			[minSizeStr:'ABC'] || 10 
//Can't predict 'size'			[notEqualStr:'notEqualStr 763'] || 1 
			[sizeStr:'sizeStr'] || 10 
			[textareaStr:'textareaStr'] || 10 
//Can't predict 'size'			[uniqueStr:'U 764'] || 1 
			[urlStr:'http://www.test765.com'] || 1 

	}




	void "Test deleting other TestString instance."() {//This is for creating some data to test list sorting
		when: "Delete testString"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting TestString instance."() {
		when: "Delete testString"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
