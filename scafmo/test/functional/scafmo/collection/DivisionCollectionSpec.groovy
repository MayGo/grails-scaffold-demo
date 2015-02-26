package scafmo.collection

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import spock.lang.Specification

class DivisionCollectionSpec extends Specification implements RestQueries, AuthQueries{

	String REST_URL = "${APP_URL}/divisioncollections/v1"
	
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

	void 'Test creating another DivisionCollection instance.'() {//This is for creating some data to test list sorting
		when: 'Create divisionCollection'
			response = sendCreateWithData(){
				name = 'Division151'
				headDivision = 1
			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.name == 'Division151'
			response.json.headDivision?.id == 1
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating DivisionCollection instance.'() {
		when: 'Create divisionCollection'
			response = sendCreateWithData(){
				name = 'Division152'
				headDivision = 1
			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.name == 'Division152'
			response.json.headDivision?.id == 1
			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading DivisionCollection instance.'() {
		when: 'Read divisionCollection'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.name == 'Division152'
			response.json.headDivision?.id == 1
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading DivisionCollection instance.'() {
		when: 'Read divisionCollection id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading DivisionCollection instance.'() {
		when: 'Read divisionCollection id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting DivisionCollection instance.'() {
		when: 'Find unexisting divisionCollection'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting divisionCollection id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating DivisionCollection instance.'() {
		when: 'Update divisionCollection'
			response = sendUpdateWithData(domainId.toString()){
				name = 'Division153'
				headDivision = 1

			}
		then: 'Should return updated values'
			response.json.name == 'Division153'
			response.json.headDivision?.id == 1

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting DivisionCollection instance.'() {
		when: 'Update unexisting divisionCollection'
			response = sendUpdateWithData('9999999999'){
					name = 'Division153'
				headDivision = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting divisionCollection id not a number'
			response = sendUpdateWithData('nonexistent'){
					name = 'Division153'
				headDivision = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}
	
	void 'Test DivisionCollection list sorting.'() {
		when: 'Get divisionCollection sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get divisionCollection sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test DivisionCollection list max property query 2 items.'() {
		when: 'Get divisionCollection list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void 'Test DivisionCollection list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get divisionCollection list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get divisionCollection list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get divisionCollection list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in DivisionCollection list.'() {
		when: 'Get divisionCollection sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in DivisionCollection list.'() {
		when: 'Get divisionCollection sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in DivisionCollection list by dummy searchString.'() {
		when: 'Get divisionCollection list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in DivisionCollection list by real searchString.'() {
		when: 'Get divisionCollection list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "Division153"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in DivisionCollection list by id.'() {
		when: 'Get divisionCollection list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in DivisionCollection list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"name":"Division153"}' || 1 
			'{"headDivision":1}' || 2 
			'{"headDivisions":[1]}' || 2 

	}
	
	
	
	
	void "Test deleting other DivisionCollection instance."() {//This is for creating some data to test list sorting
		when: "Delete divisionCollection"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting DivisionCollection instance."() {
		when: "Delete divisionCollection"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}