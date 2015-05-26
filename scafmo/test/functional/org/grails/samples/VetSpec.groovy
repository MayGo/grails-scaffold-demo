package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class VetSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/vets/v1"

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

	void 'Test creating another Vet instance.'() {//This is for creating some data to test list sorting
		when: 'Create vet'
			response = sendCreateWithData(){
				firstName = 'firstName'
				lastName = 'lastName'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Vet instance.'() {
		when: 'Create vet'
			response = sendCreateWithData(){
				firstName = 'firstName'
				lastName = 'lastName'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Vet instance.'() {
		when: 'Read vet'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Vet instance.'() {
		when: 'Read vet id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Vet instance.'() {
		when: 'Read vet id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Vet instance.'() {
		when: 'Find unexisting vet'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting vet id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Vet instance.'() {
		when: 'Update vet'
			response = sendUpdateWithData(domainId.toString()){
				firstName = 'firstName'
				lastName = 'lastName'

			}
		then: 'Should return updated values'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Vet instance.'() {
		when: 'Update unexisting vet'
			response = sendUpdateWithData('9999999999'){
					firstName = 'firstName'
				lastName = 'lastName'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting vet id not a number'
			response = sendUpdateWithData('nonexistent'){
					firstName = 'firstName'
				lastName = 'lastName'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Vet list sorting.'() {
		when: 'Get vet sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get vet sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Vet list max property query 2 items.'() {
		when: 'Get vet list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test Vet list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get vet list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get vet list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get vet list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in Vet list.'() {
		when: 'Get vet sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in Vet list.'() {
		when: 'Get vet sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Vet list by dummy searchString.'() {
		when: 'Get vet list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Vet list by real searchString.'() {
		when: 'Get vet list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "lastName"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Vet list by id.'() {
		when: 'Get vet list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Vet list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Vet list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[firstName:'firstName'] || 10 
			[lastName:'lastName'] || 10 

	}




	void "Test deleting other Vet instance."() {//This is for creating some data to test list sorting
		when: "Delete vet"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Vet instance."() {
		when: "Delete vet"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
