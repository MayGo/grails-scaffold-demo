package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class VisitSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/visits"
	
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

	void 'Test creating another Visit instance.'() {//This is for creating some data to test list sorting
		when: 'Create visit'
			response = sendCreateWithData(){
				date = '2015-02-18 00:00:00.000+0200'
				description = 'description'
				pet = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.date == '2015-02-17T22:00:00Z'
			response.json.description == 'description'
			response.json.pet?.id == 1

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Visit instance.'() {
		when: 'Create visit'
			response = sendCreateWithData(){
				date = '2015-02-18 00:00:00.000+0200'
				description = 'description'
				pet = 1

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.date == '2015-02-17T22:00:00Z'
			response.json.description == 'description'
			response.json.pet?.id == 1

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading Visit instance.'() {
		when: 'Read visit'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.date == '2015-02-17T22:00:00Z'
			response.json.description == 'description'
			response.json.pet?.id == 1

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading Visit instance.'() {
		when: 'Read visit id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading Visit instance.'() {
		when: 'Read visit id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting Visit instance.'() {
		when: 'Find unexisting visit'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting visit id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating Visit instance.'() {
		when: 'Update visit'
			response = sendUpdateWithData(domainId.toString()){
				date = '2015-02-18 00:00:00.000+0200'
				description = 'description'
				pet = 1


			}
		then: 'Should return updated values'
			response.json.date == '2015-02-17T22:00:00Z'
			response.json.description == 'description'
			response.json.pet?.id == 1


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Visit instance.'() {
		when: 'Update unexisting visit'
			response = sendUpdateWithData('9999999999'){
					date = '2015-02-18 00:00:00.000+0200'
				description = 'description'
				pet = 1


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting visit id not a number'
			response = sendUpdateWithData('nonexistent'){
					date = '2015-02-18 00:00:00.000+0200'
				description = 'description'
				pet = 1


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test Visit list sorting.'() {
		when: 'Get visit sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get visit sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test Visit list max property query 2 items.'() {
		when: 'Get visit list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test Visit list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get visit list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get visit list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get visit list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in Visit list.'() {
		when: 'Get visit sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in Visit list.'() {
		when: 'Get visit sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Visit list by dummy searchString.'() {
		when: 'Get visit list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Visit list by real searchString.'() {
		when: 'Get visit list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "description"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Visit list by id.'() {
		when: 'Get visit list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in Visit list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"date":"2015-02-18 00:00:00.000+0200"}' || 10 
			'{"description":"description"}' || 10 
			'{"pet":1}' || 2 
			'{"pets":[1]}' || 2 

	}
	
	
	
	
	void "Test deleting other Visit instance."() {//This is for creating some data to test list sorting
		when: "Delete visit"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting Visit instance."() {
		when: "Delete visit"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
