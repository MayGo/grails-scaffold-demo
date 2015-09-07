package scafmo.collection


import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class PersonCollectionlessSpec extends RestQueries implements TestUtils{

	@Shared
	Long domainId

	@Shared
	Long otherDomainId

	@Shared
	def authResponse

	@Shared
	def response

	def setupSpec() {
		restBuilder = new RestBuilder()
		authResponse = sendCorrectCredentials(APP_URL)
		// Initialize RestQueries static variables
		ACCESS_TOKEN = authResponse.json.access_token
		REST_URL = "${APP_URL}/personcollectionlesss/v1"
	}

	void 'Test creating another PersonCollectionless instance.'() {//This is for creating some data to test list sorting
		when: 'Create personCollectionless'
			response = sendCreateWithData(){
				age = 453
				name = 'John451 Doe452'
				division = 1
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.age == 453
			response.json.name == 'John451 Doe452'
			response.json.division?.id == 1
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating PersonCollectionless instance.'() {
		when: 'Create personCollectionless'
			response = sendCreateWithData(){
				age = 456
				name = 'John454 Doe455'
				division = 1
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.age == 456
			response.json.name == 'John454 Doe455'
			response.json.division?.id == 1
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading PersonCollectionless instance.'() {
		when: 'Read personCollectionless'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.age == 456
			response.json.name == 'John454 Doe455'
			response.json.division?.id == 1
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading PersonCollectionless instance.'() {
		when: 'Read personCollectionless id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading PersonCollectionless instance.'() {
		when: 'Read personCollectionless id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting PersonCollectionless instance.'() {
		when: 'Find unexisting personCollectionless'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting personCollectionless id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating PersonCollectionless instance.'() {
		when: 'Update personCollectionless'
			response = sendUpdateWithData(domainId.toString()){
				age = 459
				name = 'John457 Doe458'
				division = 1

			}
		then: 'Should return updated values'
			response.json.age == 459
			response.json.name == 'John457 Doe458'
			response.json.division?.id == 1

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting PersonCollectionless instance.'() {
		when: 'Update unexisting personCollectionless'
			response = sendUpdateWithData('9999999999'){
					age = 459
				name = 'John457 Doe458'
				division = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting personCollectionless id not a number'
			response = sendUpdateWithData('nonexistent'){
					age = 459
				name = 'John457 Doe458'
				division = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test PersonCollectionless list sorting.'() {
		when: 'Get personCollectionless sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get personCollectionless sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test PersonCollectionless list max property query 2 items.'() {
		when: 'Get personCollectionless list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using PersonCollectionless list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get personCollectionless list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get personCollectionless list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get personCollectionless list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in PersonCollectionless list.'() {
		when: 'Get personCollectionless sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in PersonCollectionless list.'() {
		when: 'Get personCollectionless sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in PersonCollectionless list by dummy searchString.'() {
		when: 'Get personCollectionless list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in PersonCollectionless list by real searchString.'() {
		when: 'Get personCollectionless list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "John457 Doe458"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in PersonCollectionless list by id.'() {
		when: 'Get personCollectionless list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("PersonCollectionless list search with props '#filter' returns '#respSize' items")
	void 'Filtering in PersonCollectionless list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[age:459] || 1 
//Can't predict 'size'			[name:'John457 Doe458'] || 1 
			[division:1] || 3 
			[divisions:1] || 3 

	}




	void "Test deleting other PersonCollectionless instance."() {//This is for creating some data to test list sorting
		when: "Delete personCollectionless"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting PersonCollectionless instance."() {
		when: "Delete personCollectionless"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
