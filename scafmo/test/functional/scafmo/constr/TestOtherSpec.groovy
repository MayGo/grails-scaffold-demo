package scafmo.constr

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class TestOtherSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/testothers"
	
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

	void 'Test creating another TestOther instance.'() {//This is for creating some data to test list sorting
		when: 'Create testOther'
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = '2015-02-17 10:05:24.825+0200'
				testEnum = 'TEST_1'
				testStringType = null

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.booleanNullable == true
			response.json.testDate == '2015-02-17T08:05:24Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == null

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating TestOther instance.'() {
		when: 'Create testOther'
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = '2015-02-17 10:05:24.844+0200'
				testEnum = 'TEST_1'
				testStringType = null

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.booleanNullable == true
			response.json.testDate == '2015-02-17T08:05:24Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == null

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading TestOther instance.'() {
		when: 'Read testOther'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.booleanNullable == true
			response.json.testDate == '2015-02-17T08:05:24Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == null

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading TestOther instance.'() {
		when: 'Read testOther id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading TestOther instance.'() {
		when: 'Read testOther id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting TestOther instance.'() {
		when: 'Find unexisting testOther'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting testOther id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating TestOther instance.'() {
		when: 'Update testOther'
			response = sendUpdateWithData(domainId.toString()){
				booleanNullable = true
				testDate = '2015-02-17 10:05:24.850+0200'
				testEnum = 'TEST_1'
				testStringType = null


			}
		then: 'Should return updated values'
			response.json.booleanNullable == true
			response.json.testDate == '2015-02-17T08:05:24Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == null


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting TestOther instance.'() {
		when: 'Update unexisting testOther'
			response = sendUpdateWithData('9999999999'){
					booleanNullable = true
				testDate = '2015-02-17 10:05:24.850+0200'
				testEnum = 'TEST_1'
				testStringType = null


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting testOther id not a number'
			response = sendUpdateWithData('nonexistent'){
					booleanNullable = true
				testDate = '2015-02-17 10:05:24.850+0200'
				testEnum = 'TEST_1'
				testStringType = null


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test TestOther list sorting.'() {
		when: 'Get testOther sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get testOther sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test TestOther list max property query 2 items.'() {
		when: 'Get testOther list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	@IgnorebooleanNullable
<scafmo.constr.TestOther@11ed241c testStringType=null booleanNullable=false testEnum=TEST_1 testDate=Tue Feb 17 10:05:24 EET 2015 errors=grails.validation.ValidationErrors: 0 errors id=null version=null $changedProperties=null>
 // have to have more then maxLimit items
	void 'Test TestOther list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get testOther list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get testOther list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get testOther list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in TestOther list.'() {
		when: 'Get testOther sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in TestOther list.'() {
		when: 'Get testOther sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in TestOther list by dummy searchString.'() {
		when: 'Get testOther list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in TestOther list by real searchString.'() {
		when: 'Get testOther list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "false"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in TestOther list by id.'() {
		when: 'Get testOther list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in TestOther list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"booleanNullable":false}' || 10 
			'{"testDate":"2015-02-17 10:05:24.850+0200"}' || 10 
			'{"testEnum":"TEST_1"}' || 10 
			'{"testStringType":null}' || 3 
			'{"testStringTypes":[null]}' || 3 

	}
	
	
	
	
	void "Test deleting other TestOther instance."() {//This is for creating some data to test list sorting
		when: "Delete testOther"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting TestOther instance."() {
		when: "Delete testOther"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
