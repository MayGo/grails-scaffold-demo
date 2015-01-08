package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class PersonCollectionSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/personcollections"
	
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
	
	void "Test creating another PersonCollection instance."() {//This is for creating some data to test list sorting
		when: "Create personCollection"
			response = sendCreateWithData(){
				age = 75
				name = 'John273 Doe274'
				division = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.age == 75
			response.json.name == 'John273 Doe274'
			response.json.division?.id == 1

			response.status == CREATED.value()
	}

	void "Test creating PersonCollection instance."() {
		when: "Create personCollection"
			response = sendCreateWithData(){
				age = 77
				name = 'John275 Doe276'
				division = 1

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.age == 77
			response.json.name == 'John275 Doe276'
			response.json.division?.id == 1

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading PersonCollection instance."() {
		when: "Read personCollection"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.age == 77
			response.json.name == 'John275 Doe276'
			response.json.division?.id == 1

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading PersonCollection instance."() {
		when: "Read personCollection id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading PersonCollection instance."() {
		when: "Read personCollection id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting PersonCollection instance."() {
		when:"Find unexisting personCollection"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting personCollection id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating PersonCollection instance."() {
		when: "Update personCollection"
			response = sendUpdateWithData(domainId.toString()){
				age = 79
				name = 'John277 Doe278'
				division = 1


			}
		then: "Should return updated values"
			response.json.age == 79
			response.json.name == 'John277 Doe278'
			response.json.division?.id == 1


			response.status == OK.value()
	}

	void "Test updating unexisting PersonCollection instance."() {
		when: "Update unexisting personCollection"
			response = sendUpdateWithData("9999999999"){
					age = 79
				name = 'John277 Doe278'
				division = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting personCollection id not a number"
			response = sendUpdateWithData("nonexistent"){
					age = 79
				name = 'John277 Doe278'
				division = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test PersonCollection list sorting."() {
		when:"Get personCollection sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get personCollection sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test PersonCollection list max property."() {
		when:"Get personCollection list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test PersonCollection list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get personCollection list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get personCollection list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in PersonCollection list."() {
		when:"Get personCollection sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in PersonCollection list."() {
		when:"Get personCollection sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in PersonCollection list."() {
		when:"Get personCollection sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other PersonCollection instance."() {//This is for creating some data to test list sorting
		when: "Delete personCollection"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting PersonCollection instance."() {
		when: "Delete personCollection"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
