package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class PersonSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/persons"
	
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

	void 'Test creating another Person instance.'() {//This is for creating some data to test list sorting
		when: 'Create person'
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

	void 'Test creating Person instance.'() {
		when: 'Create person'
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
	
	
			
		

	void 'Test reading Person instance.'() {
		when: 'Read person'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading Person instance.'() {
		when: 'Read person id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading Person instance.'() {
		when: 'Read person id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting Person instance.'() {
		when: 'Find unexisting person'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting person id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating Person instance.'() {
		when: 'Update person'
			response = sendUpdateWithData(domainId.toString()){
				firstName = 'firstName'
				lastName = 'lastName'


			}
		then: 'Should return updated values'
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Person instance.'() {
		when: 'Update unexisting person'
			response = sendUpdateWithData('9999999999'){
					firstName = 'firstName'
				lastName = 'lastName'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting person id not a number'
			response = sendUpdateWithData('nonexistent'){
					firstName = 'firstName'
				lastName = 'lastName'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test Person list sorting.'() {
		when: 'Get person sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get person sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test Person list max property query 2 items.'() {
		when: 'Get person list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test Person list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get person list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get person list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get person list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in Person list.'() {
		when: 'Get person sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in Person list.'() {
		when: 'Get person sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Person list by dummy searchString.'() {
		when: 'Get person list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Person list by real searchString.'() {
		when: 'Get person list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "firstName"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Person list by id.'() {
		when: 'Get person list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in Person list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"firstName":"firstName"}' || 10 
			'{"lastName":"lastName"}' || 10 

	}
	
	
	
	
	void "Test deleting other Person instance."() {//This is for creating some data to test list sorting
		when: "Delete person"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting Person instance."() {
		when: "Delete person"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
