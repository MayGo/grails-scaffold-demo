package org.grails.samples


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class PetTypeSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/pettypes"
	
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
	
	void "Test creating another PetType instance."() {//This is for creating some data to test list sorting
		when: "Create petType"
			response = sendCreateWithData(){
				name = 'name'

			}
			otherDomainId = response.json.id
			
		then: "Should create and return created values"
		
			response.json.name == 'name'

			response.status == CREATED.value()
	}

	void "Test creating PetType instance."() {
		when: "Create petType"
			response = sendCreateWithData(){
				name = 'name'

			}
			domainId = response.json.id
			
		then: "Should create and return created values"
			response.json.name == 'name'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading PetType instance."() {
		when: "Read petType"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			response.json.name == 'name'

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading PetType instance."() {
		when: "Read petType id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading PetType instance."() {
		when: "Read petType id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting PetType instance."() {
		when:"Find unexisting petType"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting petType id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating PetType instance."() {
		when: "Update petType"
			response = sendUpdateWithData(domainId.toString()){
				name = 'name'


			}
		then: "Should return updated values"
			response.json.name == 'name'


			response.status == OK.value()
	}

	void "Test updating unexisting PetType instance."() {
		when: "Update unexisting petType"
			response = sendUpdateWithData("9999999999"){
					name = 'name'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting petType id not a number"
			response = sendUpdateWithData("nonexistent"){
					name = 'name'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test PetType list sorting."() {
		when:"Get petType sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get petType sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test PetType list max property."() {
		when:"Get petType list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test PetType list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get petType list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get petType list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in PetType list."() {
		when:"Get petType sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in PetType list."() {
		when:"Get petType sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in PetType list."() {
		when:"Get petType sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other PetType instance."() {//This is for creating some data to test list sorting
		when: "Delete petType"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting PetType instance."() {
		when: "Delete petType"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
