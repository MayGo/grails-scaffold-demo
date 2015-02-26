package org.example.pomodoro

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class TaskSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/tasks/v1"
	
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

	void 'Test creating another Task instance.'() {//This is for creating some data to test list sorting
		when: 'Create task'
			response = sendCreateWithData(){
				deadline = '2015-02-26 00:00:00.000+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 151'
				timeSpent = 0
			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.deadline == '2015-02-25T22:00:00Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 151'
			response.json.timeSpent == 0
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Task instance.'() {
		when: 'Create task'
			response = sendCreateWithData(){
				deadline = '2015-02-26 00:00:00.000+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 152'
				timeSpent = 0
			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.deadline == '2015-02-25T22:00:00Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 152'
			response.json.timeSpent == 0
			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading Task instance.'() {
		when: 'Read task'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.deadline == '2015-02-25T22:00:00Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 152'
			response.json.timeSpent == 0
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading Task instance.'() {
		when: 'Read task id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading Task instance.'() {
		when: 'Read task id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting Task instance.'() {
		when: 'Find unexisting task'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting task id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating Task instance.'() {
		when: 'Update task'
			response = sendUpdateWithData(domainId.toString()){
				deadline = '2015-02-26 00:00:00.000+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 153'
				timeSpent = 0

			}
		then: 'Should return updated values'
			response.json.deadline == '2015-02-25T22:00:00Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 153'
			response.json.timeSpent == 0

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Task instance.'() {
		when: 'Update unexisting task'
			response = sendUpdateWithData('9999999999'){
					deadline = '2015-02-26 00:00:00.000+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 153'
				timeSpent = 0

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting task id not a number'
			response = sendUpdateWithData('nonexistent'){
					deadline = '2015-02-26 00:00:00.000+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 153'
				timeSpent = 0

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}
	
	void 'Test Task list sorting.'() {
		when: 'Get task sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get task sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test Task list max property query 2 items.'() {
		when: 'Get task list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test Task list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get task list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get task list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get task list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in Task list.'() {
		when: 'Get task sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in Task list.'() {
		when: 'Get task sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Task list by dummy searchString.'() {
		when: 'Get task list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Task list by real searchString.'() {
		when: 'Get task list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "details"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Task list by id.'() {
		when: 'Get task list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in Task list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"deadline":"2015-02-26 00:00:00.000+0200"}' || 10 
			'{"details":"details"}' || 10 
			'{"status":"Open"}' || 10 
//Can't predict 'size'			'{"summary":"Work Summary 153"}' || 1 
			'{"timeSpent":0}' || 10 

	}
	
	
	
	
	void "Test deleting other Task instance."() {//This is for creating some data to test list sorting
		when: "Delete task"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting Task instance."() {
		when: "Delete task"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}