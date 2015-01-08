package org.example.pomodoro


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class TaskSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/tasks"
	
	@Shared
	Long domainId
	@Shared
	Long otherDomainId
	
	@Shared
	def authResponse
	
	@Shared
	def response
	
	def setupSpec() {
		authResponse = sendCorrectCredentials()
	}
	
	void "Test creating another Task instance."() {//This is for creating some data to test list sorting
		when: "Create task"
			response = sendCreateWithData(){
				deadline = '2015-01-08 10:10:37.915+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 137'
				timeSpent = 0

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.deadline == '2015-01-08T08:10:37Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 137'
			response.json.timeSpent == 0

			response.status == CREATED.value()
	}

	void "Test creating Task instance."() {
		when: "Create task"
			response = sendCreateWithData(){
				deadline = '2015-01-08 10:10:37.923+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 138'
				timeSpent = 0

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.deadline == '2015-01-08T08:10:37Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 138'
			response.json.timeSpent == 0

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading Task instance."() {
		when: "Read task"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.deadline == '2015-01-08T08:10:37Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 138'
			response.json.timeSpent == 0

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading Task instance."() {
		when: "Read task id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading Task instance."() {
		when: "Read task id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting Task instance."() {
		when:"Find unexisting task"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting task id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating Task instance."() {
		when: "Update task"
			response = sendUpdateWithData(domainId.toString()){
				deadline = '2015-01-08 10:10:37.925+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 139'
				timeSpent = 0


			}
		then: "Should return updated values"
			response.json.deadline == '2015-01-08T08:10:37Z'
			response.json.details == 'details'
			response.json.status == 'Open'
			response.json.summary == 'Work Summary 139'
			response.json.timeSpent == 0


			response.status == OK.value()
	}

	void "Test updating unexisting Task instance."() {
		when: "Update unexisting task"
			response = sendUpdateWithData("9999999999"){
					deadline = '2015-01-08 10:10:37.925+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 139'
				timeSpent = 0


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting task id not a number"
			response = sendUpdateWithData("nonexistent"){
					deadline = '2015-01-08 10:10:37.925+0200'
				details = 'details'
				status = 'Open'
				summary = 'Work Summary 139'
				timeSpent = 0


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test Task list sorting."() {
		when:"Get task sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get task sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test Task list max property."() {
		when:"Get task list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test Task list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get task list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get task list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in Task list."() {
		when:"Get task sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in Task list."() {
		when:"Get task sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in Task list."() {
		when:"Get task sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other Task instance."() {//This is for creating some data to test list sorting
		when: "Delete task"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting Task instance."() {
		when: "Delete task"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
