package scafmo.collection

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class PersonCollectionSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/personcollections"
	
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

	void 'Test creating another PersonCollection instance.'() {//This is for creating some data to test list sorting
		when: 'Create personCollection'
			response = sendCreateWithData(){
				age = 0
				name = 'name'
				division = null

			}
			
			otherDomainId = response.json.id
			
			
		then: 'Should create and return created values'
			response.json.age == 0
			response.json.name == 'name'
			response.json.division?.id == null

			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating PersonCollection instance.'() {
		when: 'Create personCollection'
			response = sendCreateWithData(){
				age = 0
				name = 'name'
				division = null

			}
			
			domainId = response.json.id
			
			
		then: 'Should create and return created values'
			
			response.json.age == 0
			response.json.name == 'name'
			response.json.division?.id == null

			response.status == HttpStatus.CREATED.value()
	}
	
	
			
		

	void 'Test reading PersonCollection instance.'() {
		when: 'Read personCollection'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'
			
			response.json.age == 0
			response.json.name == 'name'
			response.json.division?.id == null

			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test excluding fields from reading PersonCollection instance.'() {
		when: 'Read personCollection id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test including fields from reading PersonCollection instance.'() {
		when: 'Read personCollection id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test reading unexisting PersonCollection instance.'() {
		when: 'Find unexisting personCollection'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting personCollection id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}

	
	void 'Test updating PersonCollection instance.'() {
		when: 'Update personCollection'
			response = sendUpdateWithData(domainId.toString()){
				age = 0
				name = 'name'
				division = null


			}
		then: 'Should return updated values'
			response.json.age == 0
			response.json.name == 'name'
			response.json.division?.id == null


			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting PersonCollection instance.'() {
		when: 'Update unexisting personCollection'
			response = sendUpdateWithData('9999999999'){
					age = 0
				name = 'name'
				division = null


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
			
		when: 'Update unexisting personCollection id not a number'
			response = sendUpdateWithData('nonexistent'){
					age = 0
				name = 'name'
				division = null


			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
	}
	
	void 'Test PersonCollection list sorting.'() {
		when: 'Get personCollection sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
		
		when: 'Get personCollection sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}
	
	
	void 'Test PersonCollection list max property query 2 items.'() {
		when: 'Get personCollection list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}
	
	
	@Ignoreage
<scafmo.collection.PersonCollection@63db394 name=name age=0 errors=grails.validation.ValidationErrors: 0 errors id=null version=null division=null $changedProperties=null>
 // have to have more then maxLimit items
	void 'Test PersonCollection list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when: 'Get personCollection list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10
			
		when: 'Get personCollection list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
			
		when: 'Get personCollection list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}
	
	
	void 'Test excluding fields in PersonCollection list.'() {
		when: 'Get personCollection sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}
	
	
	void 'Test including fields in PersonCollection list.'() {
		when: 'Get personCollection sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in PersonCollection list by dummy searchString.'() {
		when: 'Get personCollection list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in PersonCollection list by real searchString.'() {
		when: 'Get personCollection list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "0"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in PersonCollection list by id.'() {
		when: 'Get personCollection list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}
	
	void 'Test filtering in PersonCollection list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"age":0}' || 10 
			'{"name":"name"}' || 10 
			'{"division":null}' || 3 
			'{"divisions":[null]}' || 3 

	}
	
	
	
	
	void "Test deleting other PersonCollection instance."() {//This is for creating some data to test list sorting
		when: "Delete personCollection"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}
	
	
	void "Test deleting PersonCollection instance."() {
		when: "Delete personCollection"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
