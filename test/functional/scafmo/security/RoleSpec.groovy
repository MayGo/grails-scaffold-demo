package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class RoleSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/roles"
	
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
	
	void "Test creating another Role instance."() {//This is for creating some data to test list sorting
		when: "Create role"
			response = sendCreateWithData(){
				authority = 'ROLE_261'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.authority == 'ROLE_261'

			response.status == CREATED.value()
	}

	void "Test creating Role instance."() {
		when: "Create role"
			response = sendCreateWithData(){
				authority = 'ROLE_262'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.authority == 'ROLE_262'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading Role instance."() {
		when: "Read role"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.authority == 'ROLE_262'

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading Role instance."() {
		when: "Read role id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading Role instance."() {
		when: "Read role id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting Role instance."() {
		when:"Find unexisting role"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting role id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating Role instance."() {
		when: "Update role"
			response = sendUpdateWithData(domainId.toString()){
				authority = 'ROLE_263'


			}
		then: "Should return updated values"
			response.json.authority == 'ROLE_263'


			response.status == OK.value()
	}

	void "Test updating unexisting Role instance."() {
		when: "Update unexisting role"
			response = sendUpdateWithData("9999999999"){
					authority = 'ROLE_263'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting role id not a number"
			response = sendUpdateWithData("nonexistent"){
					authority = 'ROLE_263'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test Role list sorting."() {
		when:"Get role sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get role sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test Role list max property."() {
		when:"Get role list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test Role list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get role list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get role list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in Role list."() {
		when:"Get role sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in Role list."() {
		when:"Get role sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in Role list."() {
		when:"Get role sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other Role instance."() {//This is for creating some data to test list sorting
		when: "Delete role"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting Role instance."() {
		when: "Delete role"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
