package org.example.pomodoro

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class TagSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/tags/v1"

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

	void 'Test creating another Tag instance.'() {//This is for creating some data to test list sorting
		when: 'Create tag'
			response = sendCreateWithData(){
				name = 'Work Tag 151'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.name == 'Work Tag 151'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating Tag instance.'() {
		when: 'Create tag'
			response = sendCreateWithData(){
				name = 'Work Tag 152'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.name == 'Work Tag 152'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading Tag instance.'() {
		when: 'Read tag'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.name == 'Work Tag 152'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading Tag instance.'() {
		when: 'Read tag id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading Tag instance.'() {
		when: 'Read tag id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting Tag instance.'() {
		when: 'Find unexisting tag'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting tag id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating Tag instance.'() {
		when: 'Update tag'
			response = sendUpdateWithData(domainId.toString()){
				name = 'Work Tag 153'

			}
		then: 'Should return updated values'
			response.json.name == 'Work Tag 153'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting Tag instance.'() {
		when: 'Update unexisting tag'
			response = sendUpdateWithData('9999999999'){
					name = 'Work Tag 153'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting tag id not a number'
			response = sendUpdateWithData('nonexistent'){
					name = 'Work Tag 153'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test Tag list sorting.'() {
		when: 'Get tag sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get tag sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test Tag list max property query 2 items.'() {
		when: 'Get tag list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test Tag list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get tag list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get tag list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get tag list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in Tag list.'() {
		when: 'Get tag sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in Tag list.'() {
		when: 'Get tag sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in Tag list by dummy searchString.'() {
		when: 'Get tag list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in Tag list by real searchString.'() {
		when: 'Get tag list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "Work Tag 153"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in Tag list by id.'() {
		when: 'Get tag list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("Tag list search with props '#filter' returns '#respSize' items")
	void 'Filtering in Tag list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
//Can't predict 'size'			[name:'Work Tag 153'] || 1 

	}




	void "Test deleting other Tag instance."() {//This is for creating some data to test list sorting
		when: "Delete tag"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting Tag instance."() {
		when: "Delete tag"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
