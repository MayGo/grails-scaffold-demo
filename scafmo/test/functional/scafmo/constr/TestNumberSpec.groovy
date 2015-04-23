package scafmo.constr

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class TestNumberSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/testnumbers/v1"

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

	void 'Test creating another TestNumber instance.'() {//This is for creating some data to test list sorting
		when: 'Create testNumber'
			response = sendCreateWithData(){
				doubleNr = 123.123
				floatNr = 123.123
				floatNrScale = 2.34
				integerNr = 301
				integerNrInList = 3
				integerNrMax = 2
				integerNrMin = 3
				integerNrNotEqual = 2
				integerNrRange = 19
				integerNrUnique = 302
				longNr = 4
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.doubleNr == 123.123
			response.json.floatNr == 123.123
			response.json.floatNrScale == 2.34
			response.json.integerNr == 301
			response.json.integerNrInList == 3
			response.json.integerNrMax == 2
			response.json.integerNrMin == 3
			response.json.integerNrNotEqual == 2
			response.json.integerNrRange == 19
			response.json.integerNrUnique == 302
			response.json.longNr == 4
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating TestNumber instance.'() {
		when: 'Create testNumber'
			response = sendCreateWithData(){
				doubleNr = 123.123
				floatNr = 123.123
				floatNrScale = 2.34
				integerNr = 303
				integerNrInList = 3
				integerNrMax = 2
				integerNrMin = 3
				integerNrNotEqual = 2
				integerNrRange = 19
				integerNrUnique = 304
				longNr = 4
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.doubleNr == 123.123
			response.json.floatNr == 123.123
			response.json.floatNrScale == 2.34
			response.json.integerNr == 303
			response.json.integerNrInList == 3
			response.json.integerNrMax == 2
			response.json.integerNrMin == 3
			response.json.integerNrNotEqual == 2
			response.json.integerNrRange == 19
			response.json.integerNrUnique == 304
			response.json.longNr == 4
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading TestNumber instance.'() {
		when: 'Read testNumber'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.doubleNr == 123.123
			response.json.floatNr == 123.123
			response.json.floatNrScale == 2.34
			response.json.integerNr == 303
			response.json.integerNrInList == 3
			response.json.integerNrMax == 2
			response.json.integerNrMin == 3
			response.json.integerNrNotEqual == 2
			response.json.integerNrRange == 19
			response.json.integerNrUnique == 304
			response.json.longNr == 4
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading TestNumber instance.'() {
		when: 'Read testNumber id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading TestNumber instance.'() {
		when: 'Read testNumber id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting TestNumber instance.'() {
		when: 'Find unexisting testNumber'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting testNumber id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating TestNumber instance.'() {
		when: 'Update testNumber'
			response = sendUpdateWithData(domainId.toString()){
				doubleNr = 123.123
				floatNr = 123.123
				floatNrScale = 2.34
				integerNr = 305
				integerNrInList = 3
				integerNrMax = 2
				integerNrMin = 3
				integerNrNotEqual = 2
				integerNrRange = 19
				integerNrUnique = 306
				longNr = 4

			}
		then: 'Should return updated values'
			response.json.doubleNr == 123.123
			response.json.floatNr == 123.123
			response.json.floatNrScale == 2.34
			response.json.integerNr == 305
			response.json.integerNrInList == 3
			response.json.integerNrMax == 2
			response.json.integerNrMin == 3
			response.json.integerNrNotEqual == 2
			response.json.integerNrRange == 19
			response.json.integerNrUnique == 306
			response.json.longNr == 4

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting TestNumber instance.'() {
		when: 'Update unexisting testNumber'
			response = sendUpdateWithData('9999999999'){
					doubleNr = 123.123
				floatNr = 123.123
				floatNrScale = 2.34
				integerNr = 305
				integerNrInList = 3
				integerNrMax = 2
				integerNrMin = 3
				integerNrNotEqual = 2
				integerNrRange = 19
				integerNrUnique = 306
				longNr = 4

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting testNumber id not a number'
			response = sendUpdateWithData('nonexistent'){
					doubleNr = 123.123
				floatNr = 123.123
				floatNrScale = 2.34
				integerNr = 305
				integerNrInList = 3
				integerNrMax = 2
				integerNrMin = 3
				integerNrNotEqual = 2
				integerNrRange = 19
				integerNrUnique = 306
				longNr = 4

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test TestNumber list sorting.'() {
		when: 'Get testNumber sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get testNumber sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test TestNumber list max property query 2 items.'() {
		when: 'Get testNumber list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test TestNumber list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get testNumber list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get testNumber list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get testNumber list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in TestNumber list.'() {
		when: 'Get testNumber sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in TestNumber list.'() {
		when: 'Get testNumber sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in TestNumber list by dummy searchString.'() {
		when: 'Get testNumber list by searchString'
			response = queryListWithUrlVariables('searchString={searchString}', [searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in TestNumber list by real searchString.'() {
		when: 'Get testNumber list by searchString'
			response = queryListWithUrlVariables('order=desc&sort=id&searchString={searchString}',
					[searchString: "4"])

		then: 'Should at least last inserted item'
			response.json[0].id == domainId
			response.json.size() > 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in TestNumber list by id.'() {
		when: 'Get testNumber list filtered by id'

			response = queryListWithUrlVariables('filter={filter}', [filter:"{id:${domainId}}"])

		then: 'Should contains one item, just inserted item.'
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == HttpStatus.OK.value()
	}

	@Unroll("TestNumber list search with props '#jsonVal' returns '#respSize' items")
	void 'Filtering in TestNumber list by all properties.'() {
		given:
			response = queryListWithUrlVariables('filter={filter}', [filter:"${jsonVal}"])
			

		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			'{}'                || 10
			'{"doubleNr":123.123}' || 10 
			'{"floatNr":123.123}' || 10 
			'{"floatNrScale":2.34}' || 10 
			'{"integerNr":305}' || 1 
			'{"integerNrInList":3}' || 10 
			'{"integerNrMax":2}' || 10 
			'{"integerNrMin":3}' || 10 
			'{"integerNrNotEqual":2}' || 10 
			'{"integerNrRange":19}' || 10 
			'{"integerNrUnique":306}' || 1 
			'{"longNr":4}' || 10 

	}




	void "Test deleting other TestNumber instance."() {//This is for creating some data to test list sorting
		when: "Delete testNumber"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting TestNumber instance."() {
		when: "Delete testNumber"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
