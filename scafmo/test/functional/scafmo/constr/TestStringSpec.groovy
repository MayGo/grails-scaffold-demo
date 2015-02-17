package scafmo.constr

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class TestStringSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/teststrings"
	
	@Shared
	Long domainId
	@Shared
	Long otherDomainId
	
	@Shared
	def authResponse
	
	@Shared
	def response
	
	def setupSpec() {
		authResponse = sendCorrectCredentials(APP_URL)
	}

	void 'Test creating another TestString instance.'() {//This is for creating some data to test list sorting
		when: 'Create testString'
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
				uniqueStr = 'uniqueStr1'
				urlStr = 'http://www.example.com'

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr1'
			response.json.urlStr == 'http://www.example.com'

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating TestString instance.'() {
		when: 'Create testString'
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
				uniqueStr = 'uniqueStr2'
				urlStr = 'http://www.example.com'

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr2'
			response.json.urlStr == 'http://www.example.com'

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading TestString instance.'() {
		when: 'Read testString'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr2'
			response.json.urlStr == 'http://www.example.com'

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
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating TestString instance.'() {
		when: 'Update testString'
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
				uniqueStr = 'uniqueStr3'
				urlStr = 'http://www.example.com'


			}
		then: 'Should return updated values'
			response.json.blankStr == 'blankStr'
			response.json.creditCardStr == '378282246310005'
			response.json.emailStr == 'a@b.com'
			response.json.inListStr == 'test1'
			response.json.matchesStr == 'matchesStr'
			response.json.maxSizeStr == 'maxSi'
			response.json.minSizeStr == 'minSizeStr'
			response.json.notEqualStr == 'notEqualStr'
			response.json.sizeStr == 'sizeStr'
			response.json.uniqueStr == 'uniqueStr3'
			response.json.urlStr == 'http://www.example.com'


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting TestString instance.'() {
		when: 'Update unexisting testString'
			response = sendUpdateWithData('9999999999'){
					blankStr = 'blankStr'
				creditCardStr = '378282246310005'
				emailStr = 'a@b.com'
				inListStr = 'test1'
				matchesStr = 'matchesStr'
				maxSizeStr = 'maxSi'
				minSizeStr = 'minSizeStr'
				notEqualStr = 'notEqualStr'
				sizeStr = 'sizeStr'
				uniqueStr = 'uniqueStr3'
				urlStr = 'http://www.example.com'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting testString id not a number'
			response = sendUpdateWithData('nonexistent'){
					blankStr = 'blankStr'
				creditCardStr = '378282246310005'
				emailStr = 'a@b.com'
				inListStr = 'test1'
				matchesStr = 'matchesStr'
				maxSizeStr = 'maxSi'
				minSizeStr = 'minSizeStr'
				notEqualStr = 'notEqualStr'
				sizeStr = 'sizeStr'
				uniqueStr = 'uniqueStr3'
				urlStr = 'http://www.example.com'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
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
	
	
	@IgnoreblankStr
<scafmo.constr.TestString@618b147b blankStr=blankStr creditCardStr=378282246310005 emailStr=a@b.com inListStr=test1 matchesStr=matchesStr maxSizeStr=maxSi minSizeStr=minSizeStr sizeStr=sizeStr notEqualStr=notEqualStr uniqueStr=uniqueStr3 urlStr=http://www.example.com errors=grails.validation.ValidationErrors: 0 errors id=null version=null $changedProperties=null>
 // have to have more then maxLimit items
	void 'Test TestString list max property.'() {
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
	
	
	void 'Test excluding fields in TestString list.'() {
		when: 'Get testString sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in TestString list.'() {
		when: 'Get testString sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in TestString list by dummy searchString.'() {
		when: 'Get testString list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in TestString list by real searchString.'() {
		when: 'Get testString list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "blankStr"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in TestString list by id.'() {
		when: 'Get testString list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in TestString list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"blankStr":"blankStr"}' || 10 
			'{"creditCardStr":"378282246310005"}' || 10 
			'{"emailStr":"a@b.com"}' || 10 
			'{"inListStr":"test1"}' || 10 
			'{"matchesStr":"matchesStr"}' || 10 
			'{"maxSizeStr":"maxSi"}' || 10 
			'{"minSizeStr":"minSizeStr"}' || 10 
			'{"notEqualStr":"notEqualStr"}' || 10 
			'{"sizeStr":"sizeStr"}' || 10 
			'{"uniqueStr":"uniqueStr3"}' || 1 
			'{"urlStr":"http://www.example.com"}' || 10 

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
