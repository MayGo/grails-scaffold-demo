package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class PetSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/pets"
	
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

	void 'Test creating another Pet instance.'() {//This is for creating some data to test list sorting
		when: 'Create pet'
			response = sendCreateWithData(){
				birthDate = '2015-02-18 00:00:00.000+0200'
				name = 'Pet 301'
				type = 1
				owner = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.birthDate == '2015-02-17T22:00:00Z'
			response.json.name == 'Pet 301'
			response.json.type?.id == 1
			response.json.owner?.id == 1

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Pet instance.'() {
		when: 'Create pet'
			response = sendCreateWithData(){
				birthDate = '2015-02-18 00:00:00.000+0200'
				name = 'Pet 302'
				type = 1
				owner = 1

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.birthDate == '2015-02-17T22:00:00Z'
			response.json.name == 'Pet 302'
			response.json.type?.id == 1
			response.json.owner?.id == 1

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading Pet instance.'() {
		when: 'Read pet'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.birthDate == '2015-02-17T22:00:00Z'
			response.json.name == 'Pet 302'
			response.json.type?.id == 1
			response.json.owner?.id == 1

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading Pet instance.'() {
		when: 'Read pet id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading Pet instance.'() {
		when: 'Read pet id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting Pet instance.'() {
		when: 'Find unexisting pet'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting pet id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating Pet instance.'() {
		when: 'Update pet'
			response = sendUpdateWithData(domainId.toString()){
				birthDate = '2015-02-18 00:00:00.000+0200'
				name = 'Pet 303'
				type = 1
				owner = 1


			}
		then: 'Should return updated values'
			response.json.birthDate == '2015-02-17T22:00:00Z'
			response.json.name == 'Pet 303'
			response.json.type?.id == 1
			response.json.owner?.id == 1


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Pet instance.'() {
		when: 'Update unexisting pet'
			response = sendUpdateWithData('9999999999'){
					birthDate = '2015-02-18 00:00:00.000+0200'
				name = 'Pet 303'
				type = 1
				owner = 1


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting pet id not a number'
			response = sendUpdateWithData('nonexistent'){
					birthDate = '2015-02-18 00:00:00.000+0200'
				name = 'Pet 303'
				type = 1
				owner = 1


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test Pet list sorting.'() {
		when: 'Get pet sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get pet sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test Pet list max property query 2 items.'() {
		when: 'Get pet list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test Pet list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get pet list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get pet list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get pet list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in Pet list.'() {
		when: 'Get pet sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in Pet list.'() {
		when: 'Get pet sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Pet list by dummy searchString.'() {
		when: 'Get pet list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Pet list by real searchString.'() {
		when: 'Get pet list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "Pet 303"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Pet list by id.'() {
		when: 'Get pet list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in Pet list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"birthDate":"2015-02-18 00:00:00.000+0200"}' || 10 
//Can't predict 'size'			'{"name":"Pet 303"}' || 1 
			'{"type":1}' || 2 
			'{"types":[1]}' || 2 
			'{"owner":1}' || 2 
			'{"owners":[1]}' || 2 

	}
	
	
	
	
	void "Test deleting other Pet instance."() {//This is for creating some data to test list sorting
		when: "Delete pet"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting Pet instance."() {
		when: "Delete pet"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
