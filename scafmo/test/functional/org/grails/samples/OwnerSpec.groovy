package org.grails.samples


import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class OwnerSpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/owners/v1"
	}

	void 'Test creating another Owner instance.'() {//This is for creating some data to test list sorting
		when: 'Create owner'
			response = sendCreateWithData(){
				address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = '555451'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == '555451'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Owner instance.'() {
		when: 'Create owner'
			response = sendCreateWithData(){
				address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = '555452'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == '555452'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Owner instance.'() {
		when: 'Read owner'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == '555452'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Owner instance.'() {
		when: 'Read owner id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Owner instance.'() {
		when: 'Read owner id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Owner instance.'() {
		when: 'Find unexisting owner'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting owner id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Owner instance.'() {
		when: 'Update owner'
			response = sendUpdateWithData(domainId.toString()){
				address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = '555453'

			}
		then: 'Should return updated values'
			response.json.address == 'address'
			response.json.city == 'city'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.json.telephone == '555453'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Owner instance.'() {
		when: 'Update unexisting owner'
			response = sendUpdateWithData('9999999999'){
					address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = '555453'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting owner id not a number'
			response = sendUpdateWithData('nonexistent'){
					address = 'address'
				city = 'city'
				firstName = 'firstName'
				lastName = 'lastName'
				telephone = '555453'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Owner list sorting.'() {
		when: 'Get owner sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get owner sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Owner list max property query 2 items.'() {
		when: 'Get owner list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using Owner list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get owner list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get owner list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get owner list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in Owner list.'() {
		when: 'Get owner sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in Owner list.'() {
		when: 'Get owner sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in Owner list by dummy searchString.'() {
		when: 'Get owner list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Owner list by real searchString.'() {
		when: 'Get owner list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "555453"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Owner list by id.'() {
		when: 'Get owner list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Owner list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Owner list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[address:'address'] || 10 
			[city:'city'] || 10 
			[firstName:'firstName'] || 10 
			[lastName:'lastName'] || 10 
			[telephone:'555453'] || 1 

	}




	void "Test deleting other Owner instance."() {//This is for creating some data to test list sorting
		when: "Delete owner"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Owner instance."() {
		when: "Delete owner"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
