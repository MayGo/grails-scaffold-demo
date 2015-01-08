package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class DivisionCollectionSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/divisioncollections"
	
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
	
	void "Test creating another DivisionCollection instance."() {//This is for creating some data to test list sorting
		when: "Create divisionCollection"
			response = sendCreateWithData(){
				name = 'Division137'
				headDivision = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.name == 'Division137'
			response.json.headDivision?.id == 1

			response.status == CREATED.value()
	}

	void "Test creating DivisionCollection instance."() {
		when: "Create divisionCollection"
			response = sendCreateWithData(){
				name = 'Division138'
				headDivision = 1

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.name == 'Division138'
			response.json.headDivision?.id == 1

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading DivisionCollection instance."() {
		when: "Read divisionCollection"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.name == 'Division138'
			response.json.headDivision?.id == 1

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading DivisionCollection instance."() {
		when: "Read divisionCollection id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading DivisionCollection instance."() {
		when: "Read divisionCollection id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting DivisionCollection instance."() {
		when:"Find unexisting divisionCollection"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting divisionCollection id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating DivisionCollection instance."() {
		when: "Update divisionCollection"
			response = sendUpdateWithData(domainId.toString()){
				name = 'Division139'
				headDivision = 1


			}
		then: "Should return updated values"
			response.json.name == 'Division139'
			response.json.headDivision?.id == 1


			response.status == OK.value()
	}

	void "Test updating unexisting DivisionCollection instance."() {
		when: "Update unexisting divisionCollection"
			response = sendUpdateWithData("9999999999"){
					name = 'Division139'
				headDivision = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting divisionCollection id not a number"
			response = sendUpdateWithData("nonexistent"){
					name = 'Division139'
				headDivision = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test DivisionCollection list sorting."() {
		when:"Get divisionCollection sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get divisionCollection sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test DivisionCollection list max property."() {
		when:"Get divisionCollection list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test DivisionCollection list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get divisionCollection list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get divisionCollection list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in DivisionCollection list."() {
		when:"Get divisionCollection sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in DivisionCollection list."() {
		when:"Get divisionCollection sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in DivisionCollection list."() {
		when:"Get divisionCollection sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other DivisionCollection instance."() {//This is for creating some data to test list sorting
		when: "Delete divisionCollection"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting DivisionCollection instance."() {
		when: "Delete divisionCollection"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
