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

class EmbedSpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/embeds/v1"
	}

	void 'Test creating another Embed instance.'() {//This is for creating some data to test list sorting
		when: 'Create embed'
			response = sendCreateWithData(){
				acCustomMap = [
  'mykey':  'myvalue'
]
				acMap = [
  'mykey':  'myvalue'
]
				acStr = 'Blank 151'
				muFileLocation = 'myfile.txt'
				myFile = '[116, 101, 115, 116, 49]'
				myEmbeddedField{
					id = null
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 151'
				}
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
//			response.json.acCustomMap == [mykey:myvalue]
//			response.json.acMap == [mykey:myvalue]
			response.json.acStr == 'Blank 151'
			response.json.muFileLocation == 'myfile.txt'
			response.json.myFile == '[116, 101, 115, 116, 49]'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Embed instance.'() {
		when: 'Create embed'
			response = sendCreateWithData(){
				acCustomMap = [
  'mykey':  'myvalue'
]
				acMap = [
  'mykey':  'myvalue'
]
				acStr = 'Blank 152'
				muFileLocation = 'myfile.txt'
				myFile = '[116, 101, 115, 116, 49]'
				myEmbeddedField{
					id = null
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 151'
				}
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

//			response.json.acCustomMap == [mykey:myvalue]
//			response.json.acMap == [mykey:myvalue]
			response.json.acStr == 'Blank 152'
			response.json.muFileLocation == 'myfile.txt'
			response.json.myFile == '[116, 101, 115, 116, 49]'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Embed instance.'() {
		when: 'Read embed'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

//			response.json.acCustomMap == [mykey:myvalue]
//			response.json.acMap == [mykey:myvalue]
			response.json.acStr == 'Blank 152'
			response.json.muFileLocation == 'myfile.txt'
			response.json.myFile == '[116, 101, 115, 116, 49]'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Embed instance.'() {
		when: 'Read embed id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Embed instance.'() {
		when: 'Read embed id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Embed instance.'() {
		when: 'Find unexisting embed'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting embed id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Embed instance.'() {
		when: 'Update embed'
			response = sendUpdateWithData(domainId.toString()){
				acCustomMap = [
  'mykey':  'myvalue'
]
				acMap = [
  'mykey':  'myvalue'
]
				acStr = 'Blank 153'
				muFileLocation = 'myfile.txt'
				myFile = '[116, 101, 115, 116, 49]'
				myEmbeddedField{
					id = null
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 151'
				}

			}
		then: 'Should return updated values'
//			response.json.acCustomMap == [mykey:myvalue]
//			response.json.acMap == [mykey:myvalue]
			response.json.acStr == 'Blank 153'
			response.json.muFileLocation == 'myfile.txt'
			response.json.myFile == '[116, 101, 115, 116, 49]'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Embed instance.'() {
		when: 'Update unexisting embed'
			response = sendUpdateWithData('9999999999'){
					acCustomMap = [
  'mykey':  'myvalue'
]
				acMap = [
  'mykey':  'myvalue'
]
				acStr = 'Blank 153'
				muFileLocation = 'myfile.txt'
				myFile = '[116, 101, 115, 116, 49]'
				myEmbeddedField{
					id = null
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 151'
				}

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting embed id not a number'
			response = sendUpdateWithData('nonexistent'){
					acCustomMap = [
  'mykey':  'myvalue'
]
				acMap = [
  'mykey':  'myvalue'
]
				acStr = 'Blank 153'
				muFileLocation = 'myfile.txt'
				myFile = '[116, 101, 115, 116, 49]'
				myEmbeddedField{
					id = null
				jsonMap = [
  'mykey':  'myvalue'
]
				myAc = [
  'mykey':  'myvalue'
]
				str = 'Blank 151'
				}

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Embed list sorting.'() {
		when: 'Get embed sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get embed sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Embed list max property query 2 items.'() {
		when: 'Get embed list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using Embed list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get embed list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get embed list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get embed list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in Embed list.'() {
		when: 'Get embed sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in Embed list.'() {
		when: 'Get embed sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in Embed list by dummy searchString.'() {
		when: 'Get embed list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Embed list by real searchString.'() {
		when: 'Get embed list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "INSERT SOMETHING SEARCHABLE"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Embed list by id.'() {
		when: 'Get embed list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Embed list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Embed list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[acCustomMaps:'%7B"mykey":"myvalue"%7D'] || 10 
			[acMaps:'%7B"mykey":"myvalue"%7D'] || 10 
//Can't predict 'size'			[acStr:'Blank 153'] || 1 
			[muFileLocation:'myfile.txt'] || 10 
			[myFile:'[116, 101, 115, 116, 49]'] || 10 

	}




	void "Test deleting other Embed instance."() {//This is for creating some data to test list sorting
		when: "Delete embed"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Embed instance."() {
		when: "Delete embed"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
