package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class DivisionCollectionlessSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/divisioncollectionlesss"
	
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
	
	void "Test creating another DivisionCollectionless instance."() {//This is for creating some data to test list sorting
		when: "Create divisionCollectionless"
			response = sendCreateWithData(){
				name = 'name'
				headDivision = null

			}
			otherDomainId = response.json.id
			
		then: "Should create and return created values"
		
			response.json.name == 'name'
			response.json.headDivision?.id == null

			response.status == CREATED.value()
	}

	void "Test creating DivisionCollectionless instance."() {
		when: "Create divisionCollectionless"
			response = sendCreateWithData(){
				name = 'name'
				headDivision = null

			}
			domainId = response.json.id
			
		then: "Should create and return created values"
			response.json.name == 'name'
			response.json.headDivision?.id == null

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading DivisionCollectionless instance."() {
		when: "Read divisionCollectionless"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			response.json.name == 'name'
			response.json.headDivision?.id == null

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading DivisionCollectionless instance."() {
		when: "Read divisionCollectionless id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading DivisionCollectionless instance."() {
		when: "Read divisionCollectionless id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting DivisionCollectionless instance."() {
		when:"Find unexisting divisionCollectionless"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting divisionCollectionless id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating DivisionCollectionless instance."() {
		when: "Update divisionCollectionless"
			response = sendUpdateWithData(domainId.toString()){
				name = 'name'
				headDivision = null


			}
		then: "Should return updated values"
			response.json.name == 'name'
			response.json.headDivision?.id == null


			response.status == OK.value()
	}

	void "Test updating unexisting DivisionCollectionless instance."() {
		when: "Update unexisting divisionCollectionless"
			response = sendUpdateWithData("9999999999"){
					name = 'name'
				headDivision = null


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting divisionCollectionless id not a number"
			response = sendUpdateWithData("nonexistent"){
					name = 'name'
				headDivision = null


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test DivisionCollectionless list sorting."() {
		when:"Get divisionCollectionless sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get divisionCollectionless sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test DivisionCollectionless list max property."() {
		when:"Get divisionCollectionless list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test DivisionCollectionless list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get divisionCollectionless list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get divisionCollectionless list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in DivisionCollectionless list."() {
		when:"Get divisionCollectionless sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in DivisionCollectionless list."() {
		when:"Get divisionCollectionless sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in DivisionCollectionless list."() {
		when:"Get divisionCollectionless sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other DivisionCollectionless instance."() {//This is for creating some data to test list sorting
		when: "Delete divisionCollectionless"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting DivisionCollectionless instance."() {
		when: "Delete divisionCollectionless"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
