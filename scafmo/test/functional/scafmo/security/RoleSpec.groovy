package scafmo.security

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class RoleSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/roles"
	
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

	void 'Test creating another Role instance.'() {//This is for creating some data to test list sorting
		when: 'Create role'
			response = sendCreateWithData(){
				authority = 'ROLE_301'

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.authority == 'ROLE_301'

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Role instance.'() {
		when: 'Create role'
			response = sendCreateWithData(){
				authority = 'ROLE_302'

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.authority == 'ROLE_302'

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading Role instance.'() {
		when: 'Read role'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.authority == 'ROLE_302'

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading Role instance.'() {
		when: 'Read role id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading Role instance.'() {
		when: 'Read role id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting Role instance.'() {
		when: 'Find unexisting role'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting role id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating Role instance.'() {
		when: 'Update role'
			response = sendUpdateWithData(domainId.toString()){
				authority = 'ROLE_303'


			}
		then: 'Should return updated values'
			response.json.authority == 'ROLE_303'


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Role instance.'() {
		when: 'Update unexisting role'
			response = sendUpdateWithData('9999999999'){
					authority = 'ROLE_303'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting role id not a number'
			response = sendUpdateWithData('nonexistent'){
					authority = 'ROLE_303'


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test Role list sorting.'() {
		when: 'Get role sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get role sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test Role list max property query 2 items.'() {
		when: 'Get role list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test Role list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get role list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get role list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get role list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in Role list.'() {
		when: 'Get role sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in Role list.'() {
		when: 'Get role sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Role list by dummy searchString.'() {
		when: 'Get role list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Role list by real searchString.'() {
		when: 'Get role list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "ROLE_303"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Role list by id.'() {
		when: 'Get role list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in Role list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"authority":"ROLE_303"}' || 1 

	}
	
	
	
	
	void "Test deleting other Role instance."() {//This is for creating some data to test list sorting
		when: "Delete role"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting Role instance."() {
		when: "Delete role"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
