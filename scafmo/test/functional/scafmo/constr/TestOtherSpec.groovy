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

class TestOtherSpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/testothers/v1"
	}

	void 'Test creating another TestOther instance.'() {//This is for creating some data to test list sorting
		when: 'Create testOther'
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = getTodayForInput()
				testEnum = 'TEST_1'
				testStringType = 1
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.booleanNullable == true
			response.json.testDate == getTodayForOutput()
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating TestOther instance.'() {
		when: 'Create testOther'
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = getTodayForInput()
				testEnum = 'TEST_1'
				testStringType = 1
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.booleanNullable == true
			response.json.testDate == getTodayForOutput()
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading TestOther instance.'() {
		when: 'Read testOther'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.booleanNullable == true
			response.json.testDate == getTodayForOutput()
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1
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
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating TestOther instance.'() {
		when: 'Update testOther'
			response = sendUpdateWithData(domainId.toString()){
				booleanNullable = true
				testDate = getTodayForInput()
				testEnum = 'TEST_1'
				testStringType = 1

			}
		then: 'Should return updated values'
			response.json.booleanNullable == true
			response.json.testDate == getTodayForOutput()
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting TestOther instance.'() {
		when: 'Update unexisting testOther'
			response = sendUpdateWithData('9999999999'){
					booleanNullable = true
				testDate = getTodayForInput()
				testEnum = 'TEST_1'
				testStringType = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting testOther id not a number'
			response = sendUpdateWithData('nonexistent'){
					booleanNullable = true
				testDate = getTodayForInput()
				testEnum = 'TEST_1'
				testStringType = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
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


	 // have to have more then maxLimit items
	void 'Using TestOther list max property.'() {
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

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in TestOther list.'() {
		when: 'Get testOther sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in TestOther list.'() {
		when: 'Get testOther sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in TestOther list by dummy searchString.'() {
		when: 'Get testOther list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in TestOther list by real searchString.'() {
		when: 'Get testOther list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "TEST_1"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in TestOther list by id.'() {
		when: 'Get testOther list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("TestOther list search with props '#filter' returns '#respSize' items")
	void 'Filtering in TestOther list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[booleanNullable:false] || 10 
			[testDate:'' + getTodayForInput() + ''] || 10 
			[testEnum:'TEST_1'] || 10 
			[testStringType:1] || 3 
			[testStringTypes:1] || 3 

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
