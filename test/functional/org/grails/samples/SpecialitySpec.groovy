package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class SpecialitySpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/specialitys"
	
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
	
	void "Test creating another Speciality instance."() {//This is for creating some data to test list sorting
		when: "Create speciality"
			response = sendCreateWithData(){
				name = 'Speciality 137'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.name == 'Speciality 137'

			response.status == CREATED.value()
	}

	void "Test creating Speciality instance."() {
		when: "Create speciality"
			response = sendCreateWithData(){
				name = 'Speciality 138'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.name == 'Speciality 138'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading Speciality instance."() {
		when: "Read speciality"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.name == 'Speciality 138'

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading Speciality instance."() {
		when: "Read speciality id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading Speciality instance."() {
		when: "Read speciality id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting Speciality instance."() {
		when:"Find unexisting speciality"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting speciality id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating Speciality instance."() {
		when: "Update speciality"
			response = sendUpdateWithData(domainId.toString()){
				name = 'Speciality 139'


			}
		then: "Should return updated values"
			response.json.name == 'Speciality 139'


			response.status == OK.value()
	}

	void "Test updating unexisting Speciality instance."() {
		when: "Update unexisting speciality"
			response = sendUpdateWithData("9999999999"){
					name = 'Speciality 139'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting speciality id not a number"
			response = sendUpdateWithData("nonexistent"){
					name = 'Speciality 139'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test Speciality list sorting."() {
		when:"Get speciality sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get speciality sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test Speciality list max property."() {
		when:"Get speciality list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test Speciality list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get speciality list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get speciality list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in Speciality list."() {
		when:"Get speciality sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in Speciality list."() {
		when:"Get speciality sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in Speciality list."() {
		when:"Get speciality sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other Speciality instance."() {//This is for creating some data to test list sorting
		when: "Delete speciality"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting Speciality instance."() {
		when: "Delete speciality"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
