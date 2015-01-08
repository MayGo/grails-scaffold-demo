package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class VetSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/vets"
	
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
	
	void "Test creating another Vet instance."() {//This is for creating some data to test list sorting
		when: "Create vet"
			response = sendCreateWithData(){
				firstName = 'firstName'
				lastName = 'lastName'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'

			response.status == CREATED.value()
	}

	void "Test creating Vet instance."() {
		when: "Create vet"
			response = sendCreateWithData(){
				firstName = 'firstName'
				lastName = 'lastName'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading Vet instance."() {
		when: "Read vet"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading Vet instance."() {
		when: "Read vet id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading Vet instance."() {
		when: "Read vet id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting Vet instance."() {
		when:"Find unexisting vet"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting vet id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating Vet instance."() {
		when: "Update vet"
			response = sendUpdateWithData(domainId.toString()){
				firstName = 'firstName'
				lastName = 'lastName'


			}
		then: "Should return updated values"
			response.json.firstName == 'firstName'
			response.json.lastName == 'lastName'


			response.status == OK.value()
	}

	void "Test updating unexisting Vet instance."() {
		when: "Update unexisting vet"
			response = sendUpdateWithData("9999999999"){
					firstName = 'firstName'
				lastName = 'lastName'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting vet id not a number"
			response = sendUpdateWithData("nonexistent"){
					firstName = 'firstName'
				lastName = 'lastName'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test Vet list sorting."() {
		when:"Get vet sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get vet sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test Vet list max property."() {
		when:"Get vet list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test Vet list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get vet list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get vet list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in Vet list."() {
		when:"Get vet sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in Vet list."() {
		when:"Get vet sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in Vet list."() {
		when:"Get vet sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other Vet instance."() {//This is for creating some data to test list sorting
		when: "Delete vet"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting Vet instance."() {
		when: "Delete vet"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
