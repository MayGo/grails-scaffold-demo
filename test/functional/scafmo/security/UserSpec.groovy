package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class UserSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/users"
	
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
	
	void "Test creating another User instance."() {//This is for creating some data to test list sorting
		when: "Create user"
			response = sendCreateWithData(){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 261'

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 261'

			response.status == CREATED.value()
	}

	void "Test creating User instance."() {
		when: "Create user"
			response = sendCreateWithData(){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 262'

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 262'

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading User instance."() {
		when: "Read user"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 262'

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading User instance."() {
		when: "Read user id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading User instance."() {
		when: "Read user id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting User instance."() {
		when:"Find unexisting user"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting user id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating User instance."() {
		when: "Update user"
			response = sendUpdateWithData(domainId.toString()){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 263'


			}
		then: "Should return updated values"
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 263'


			response.status == OK.value()
	}

	void "Test updating unexisting User instance."() {
		when: "Update unexisting user"
			response = sendUpdateWithData("9999999999"){
					accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 263'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting user id not a number"
			response = sendUpdateWithData("nonexistent"){
					accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 263'


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test User list sorting."() {
		when:"Get user sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get user sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test User list max property."() {
		when:"Get user list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test User list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get user list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get user list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in User list."() {
		when:"Get user sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in User list."() {
		when:"Get user sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in User list."() {
		when:"Get user sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other User instance."() {//This is for creating some data to test list sorting
		when: "Delete user"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting User instance."() {
		when: "Delete user"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
