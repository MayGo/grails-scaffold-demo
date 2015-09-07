package org.grails.samples


import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class VisitSpec extends RestQueries implements TestUtils{

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
		REST_URL = "${APP_URL}/visits/v1"
	}

	void 'Test creating another Visit instance.'() {//This is for creating some data to test list sorting
		when: 'Create visit'
			response = sendCreateWithData(){
				date = getTodayForInput()
				description = 'description'
				pet = 1
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.date == getTodayForOutput()
			response.json.description == 'description'
			response.json.pet?.id == 1
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Visit instance.'() {
		when: 'Create visit'
			response = sendCreateWithData(){
				date = getTodayForInput()
				description = 'description'
				pet = 1
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.date == getTodayForOutput()
			response.json.description == 'description'
			response.json.pet?.id == 1
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Visit instance.'() {
		when: 'Read visit'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.date == getTodayForOutput()
			response.json.description == 'description'
			response.json.pet?.id == 1
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Visit instance.'() {
		when: 'Read visit id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Visit instance.'() {
		when: 'Read visit id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Visit instance.'() {
		when: 'Find unexisting visit'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting visit id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Visit instance.'() {
		when: 'Update visit'
			response = sendUpdateWithData(domainId.toString()){
				date = getTodayForInput()
				description = 'description'
				pet = 1

			}
		then: 'Should return updated values'
			response.json.date == getTodayForOutput()
			response.json.description == 'description'
			response.json.pet?.id == 1

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Visit instance.'() {
		when: 'Update unexisting visit'
			response = sendUpdateWithData('9999999999'){
					date = getTodayForInput()
				description = 'description'
				pet = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting visit id not a number'
			response = sendUpdateWithData('nonexistent'){
					date = getTodayForInput()
				description = 'description'
				pet = 1

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Visit list sorting.'() {
		when: 'Get visit sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get visit sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Visit list max property query 2 items.'() {
		when: 'Get visit list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Using Visit list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get visit list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get visit list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get visit list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}

	@Ignore // Excluding not working in grails>2.4.3
	void 'Excluding "ID" field in Visit list.'() {
		when: 'Get visit sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}

	@Ignore // Including not working in grails>2.4.3
	void 'Including "ID" in Visit list.'() {
		when: 'Get visit sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'Id is not empty'
			response.json[0].id != null
	}

	void 'Test querying in Visit list by dummy searchString.'() {
		when: 'Get visit list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Visit list by real searchString.'() {
		when: 'Get visit list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "description"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Visit list by id.'() {
		when: 'Get visit list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Visit list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Visit list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
			[date:'' + getTodayForInput() + ''] || 10 
			[description:'description'] || 10 
			[pet:1] || 3 
			[pets:1] || 3 

	}




	void "Test deleting other Visit instance."() {//This is for creating some data to test list sorting
		when: "Delete visit"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Visit instance."() {
		when: "Delete visit"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
