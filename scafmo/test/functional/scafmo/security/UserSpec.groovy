package scafmo.security

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class UserSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/users/v1"

	@Shared
	Long domainId
	@Shared
	Long otherDomainId

	@Shared
	def authResponse

	@Shared
	def response

	def setupSpec() {
		authResponse = sendCorrectCredentials(APP_URL)
	}

	void 'Test creating another User instance.'() {//This is for creating some data to test list sorting
		when: 'Create user'
			response = sendCreateWithData(){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 301'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 301'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating User instance.'() {
		when: 'Create user'
			response = sendCreateWithData(){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 302'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 302'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading User instance.'() {
		when: 'Read user'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 302'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading User instance.'() {
		when: 'Read user id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading User instance.'() {
		when: 'Read user id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting User instance.'() {
		when: 'Find unexisting user'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting user id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating User instance.'() {
		when: 'Update user'
			response = sendUpdateWithData(domainId.toString()){
				accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 303'

			}
		then: 'Should return updated values'
			response.json.accountExpired == true
			response.json.accountLocked == true
			response.json.enabled == true
			response.json.passwordExpired == true
			response.json.username == 'John Doe 303'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting User instance.'() {
		when: 'Update unexisting user'
			response = sendUpdateWithData('9999999999'){
					accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 303'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting user id not a number'
			response = sendUpdateWithData('nonexistent'){
					accountExpired = true
				accountLocked = true
				enabled = true
				passwordExpired = true
				username = 'John Doe 303'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test User list sorting.'() {
		when: 'Get user sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get user sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test User list max property query 2 items.'() {
		when: 'Get user list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test User list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get user list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get user list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get user list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in User list.'() {
		when: 'Get user sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in User list.'() {
		when: 'Get user sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in User list by dummy searchString.'() {
		when: 'Get user list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in User list by real searchString.'() {
		when: 'Get user list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "John Doe 303"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in User list by id.'() {
		when: 'Get user list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}

	@Unroll("User list search with props '#jsonVal' returns '#respSize' items")
	void 'Filtering in User list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			

		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"accountExpired":false}' || 10 
			'{"accountLocked":false}' || 10 
			'{"enabled":true}' || 10 
			'{"passwordExpired":false}' || 10 
//Can't predict 'size'			'{"username":"John Doe 303"}' || 1 

	}




	void "Test deleting other User instance."() {//This is for creating some data to test list sorting
		when: "Delete user"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting User instance."() {
		when: "Delete user"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
