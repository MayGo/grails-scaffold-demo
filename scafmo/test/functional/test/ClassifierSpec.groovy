package test

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class ClassifierSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/classifiers/v1"

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

	void 'Test creating another Classifier instance.'() {//This is for creating some data to test list sorting
		when: 'Create classifier'
			response = sendCreateWithData(){
				classname = 'classname'
				constant = 'constant'
				description = 'description'
				propertyname = 'propertyname'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.classname == 'classname'
			response.json.constant == 'constant'
			response.json.description == 'description'
			response.json.propertyname == 'propertyname'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Classifier instance.'() {
		when: 'Create classifier'
			response = sendCreateWithData(){
				classname = 'classname'
				constant = 'constant'
				description = 'description'
				propertyname = 'propertyname'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.classname == 'classname'
			response.json.constant == 'constant'
			response.json.description == 'description'
			response.json.propertyname == 'propertyname'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Classifier instance.'() {
		when: 'Read classifier'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.classname == 'classname'
			response.json.constant == 'constant'
			response.json.description == 'description'
			response.json.propertyname == 'propertyname'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Classifier instance.'() {
		when: 'Read classifier id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Classifier instance.'() {
		when: 'Read classifier id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Classifier instance.'() {
		when: 'Find unexisting classifier'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting classifier id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Classifier instance.'() {
		when: 'Update classifier'
			response = sendUpdateWithData(domainId.toString()){
				classname = 'classname'
				constant = 'constant'
				description = 'description'
				propertyname = 'propertyname'

			}
		then: 'Should return updated values'
			response.json.classname == 'classname'
			response.json.constant == 'constant'
			response.json.description == 'description'
			response.json.propertyname == 'propertyname'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Classifier instance.'() {
		when: 'Update unexisting classifier'
			response = sendUpdateWithData('9999999999'){
					classname = 'classname'
				constant = 'constant'
				description = 'description'
				propertyname = 'propertyname'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting classifier id not a number'
			response = sendUpdateWithData('nonexistent'){
					classname = 'classname'
				constant = 'constant'
				description = 'description'
				propertyname = 'propertyname'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Classifier list sorting.'() {
		when: 'Get classifier sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get classifier sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Classifier list max property query 2 items.'() {
		when: 'Get classifier list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test Classifier list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get classifier list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get classifier list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get classifier list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in Classifier list.'() {
		when: 'Get classifier sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in Classifier list.'() {
		when: 'Get classifier sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Classifier list by dummy searchString.'() {
		when: 'Get classifier list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Classifier list by real searchString.'() {
		when: 'Get classifier list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "propertyname"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Classifier list by id.'() {
		when: 'Get classifier list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Classifier list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Classifier list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[classname:'classname'] || 10 
			[constant:'constant'] || 10 
			[description:'description'] || 10 
			[propertyname:'propertyname'] || 10 

	}




	void "Test deleting other Classifier instance."() {//This is for creating some data to test list sorting
		when: "Delete classifier"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Classifier instance."() {
		when: "Delete classifier"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
