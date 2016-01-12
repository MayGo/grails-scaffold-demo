package scafmo.embedded


import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class EmbeddableSpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/embeddables/v1"
	}

	void 'Test creating another Embeddable instance.'() {//This is for creating some data to test list sorting
		when: 'Create embeddable'
			response = sendCreateWithData(){
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 152'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
//			response.json.jsonMap == [mykey:myvalue]
//			response.json.myAc == [mykey:myvalue]
			response.json.str == 'Blank 152'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Embeddable instance.'() {
		when: 'Create embeddable'
			response = sendCreateWithData(){
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 153'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

//			response.json.jsonMap == [mykey:myvalue]
//			response.json.myAc == [mykey:myvalue]
			response.json.str == 'Blank 153'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Embeddable instance.'() {
		when: 'Read embeddable'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

//			response.json.jsonMap == [mykey:myvalue]
//			response.json.myAc == [mykey:myvalue]
			response.json.str == 'Blank 153'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Embeddable instance.'() {
		when: 'Read embeddable id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Embeddable instance.'() {
		when: 'Read embeddable id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Embeddable instance.'() {
		when: 'Find unexisting embeddable'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting embeddable id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Embeddable instance.'() {
		when: 'Update embeddable'
			response = sendUpdateWithData(domainId.toString()){
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 154'

			}
		then: 'Should return updated values'
//			response.json.jsonMap == [mykey:myvalue]
//			response.json.myAc == [mykey:myvalue]
			response.json.str == 'Blank 154'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Embeddable instance.'() {
		when: 'Update unexisting embeddable'
			response = sendUpdateWithData('9999999999'){
					jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 154'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting embeddable id not a number'
			response = sendUpdateWithData('nonexistent'){
					jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 154'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Embeddable list sorting.'() {
		when: 'Get embeddable sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get embeddable sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Embeddable list max property query 2 items.'() {
		when: 'Get embeddable list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using Embeddable list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get embeddable list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get embeddable list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get embeddable list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in Embeddable list.'() {
		when: 'Get embeddable sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in Embeddable list.'() {
		when: 'Get embeddable sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in Embeddable list by dummy searchString.'() {
		when: 'Get embeddable list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Embeddable list by real searchString.'() {
		when: 'Get embeddable list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "INSERT SOMETHING SEARCHABLE"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Embeddable list by id.'() {
		when: 'Get embeddable list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Embeddable list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Embeddable list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[jsonMaps:'%7B"mykey":"myvalue"%7D'] || 10 
			[myAcs:'%7B"mykey":"myvalue"%7D'] || 10 
//Can't predict 'size'			[str:'Blank 154'] || 1 

	}




	void "Test deleting other Embeddable instance."() {//This is for creating some data to test list sorting
		when: "Delete embeddable"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Embeddable instance."() {
		when: "Delete embeddable"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
