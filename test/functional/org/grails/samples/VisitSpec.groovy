package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class VisitSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/visits"
	
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
	
	void "Test creating another Visit instance."() {//This is for creating some data to test list sorting
		when: "Create visit"
			response = sendCreateWithData(){
				date = '2015-01-07 10:06:06.566+0200'
				description = 'description'
				pet = null

			}
			otherDomainId = response.json.id
			
		then: "Should create and return created values"
		
			response.json.date == '2015-01-07T08:06:06Z'
			response.json.description == 'description'
			response.json.pet?.id == null

			response.status == CREATED.value()
	}

	void "Test creating Visit instance."() {
		when: "Create visit"
			response = sendCreateWithData(){
				date = '2015-01-07 10:06:06.566+0200'
				description = 'description'
				pet = null

			}
			domainId = response.json.id
			
		then: "Should create and return created values"
			response.json.date == '2015-01-07T08:06:06Z'
			response.json.description == 'description'
			response.json.pet?.id == null

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading Visit instance."() {
		when: "Read visit"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			response.json.date == '2015-01-07T08:06:06Z'
			response.json.description == 'description'
			response.json.pet?.id == null

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading Visit instance."() {
		when: "Read visit id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading Visit instance."() {
		when: "Read visit id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting Visit instance."() {
		when:"Find unexisting visit"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting visit id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating Visit instance."() {
		when: "Update visit"
			response = sendUpdateWithData(domainId.toString()){
				date = '2015-01-07 10:06:06.581+0200'
				description = 'description'
				pet = null


			}
		then: "Should return updated values"
			response.json.date == '2015-01-07T08:06:06Z'
			response.json.description == 'description'
			response.json.pet?.id == null


			response.status == OK.value()
	}

	void "Test updating unexisting Visit instance."() {
		when: "Update unexisting visit"
			response = sendUpdateWithData("9999999999"){
					date = '2015-01-07 10:06:06.581+0200'
				description = 'description'
				pet = null


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting visit id not a number"
			response = sendUpdateWithData("nonexistent"){
					date = '2015-01-07 10:06:06.581+0200'
				description = 'description'
				pet = null


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test Visit list sorting."() {
		when:"Get visit sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get visit sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test Visit list max property."() {
		when:"Get visit list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test Visit list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get visit list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get visit list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in Visit list."() {
		when:"Get visit sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in Visit list."() {
		when:"Get visit sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in Visit list."() {
		when:"Get visit sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other Visit instance."() {//This is for creating some data to test list sorting
		when: "Delete visit"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting Visit instance."() {
		when: "Delete visit"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
