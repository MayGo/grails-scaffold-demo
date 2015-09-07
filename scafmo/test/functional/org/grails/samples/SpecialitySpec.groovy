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

class SpecialitySpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/specialitys/v1"
	}

	void 'Test creating another Speciality instance.'() {//This is for creating some data to test list sorting
		when: 'Create speciality'
			response = sendCreateWithData(){
				name = 'Speciality 151'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.name == 'Speciality 151'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Speciality instance.'() {
		when: 'Create speciality'
			response = sendCreateWithData(){
				name = 'Speciality 152'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.name == 'Speciality 152'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Speciality instance.'() {
		when: 'Read speciality'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.name == 'Speciality 152'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Speciality instance.'() {
		when: 'Read speciality id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Speciality instance.'() {
		when: 'Read speciality id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Speciality instance.'() {
		when: 'Find unexisting speciality'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting speciality id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Speciality instance.'() {
		when: 'Update speciality'
			response = sendUpdateWithData(domainId.toString()){
				name = 'Speciality 153'

			}
		then: 'Should return updated values'
			response.json.name == 'Speciality 153'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Speciality instance.'() {
		when: 'Update unexisting speciality'
			response = sendUpdateWithData('9999999999'){
					name = 'Speciality 153'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting speciality id not a number'
			response = sendUpdateWithData('nonexistent'){
					name = 'Speciality 153'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Speciality list sorting.'() {
		when: 'Get speciality sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get speciality sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Speciality list max property query 2 items.'() {
		when: 'Get speciality list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using Speciality list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get speciality list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get speciality list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get speciality list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in Speciality list.'() {
		when: 'Get speciality sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in Speciality list.'() {
		when: 'Get speciality sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in Speciality list by dummy searchString.'() {
		when: 'Get speciality list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Speciality list by real searchString.'() {
		when: 'Get speciality list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "Speciality 153"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Speciality list by id.'() {
		when: 'Get speciality list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Speciality list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Speciality list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
//Can't predict 'size'			[name:'Speciality 153'] || 1 

	}




	void "Test deleting other Speciality instance."() {//This is for creating some data to test list sorting
		when: "Delete speciality"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Speciality instance."() {
		when: "Delete speciality"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
