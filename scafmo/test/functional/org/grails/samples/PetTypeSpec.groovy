package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class PetTypeSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/pettypes/v1"

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

	void 'Test creating another PetType instance.'() {//This is for creating some data to test list sorting
		when: 'Create petType'
			response = sendCreateWithData(){
				name = 'Type 454'
			}
			
			otherDomainId = response.json.id
			

		then: 'Should create and return created values'
			response.json.name == 'Type 454'
			response.status == HttpStatus.CREATED.value()
	}

	void 'Test creating PetType instance.'() {
		when: 'Create petType'
			response = sendCreateWithData(){
				name = 'Type 455'
			}
			
			domainId = response.json.id
			

		then: 'Should create and return created values'

			response.json.name == 'Type 455'
			response.status == HttpStatus.CREATED.value()
	}





	void 'Test reading PetType instance.'() {
		when: 'Read petType'
			response = readDomainItemWithParams(domainId.toString(), "")
		then: 'Should return correct values'

			response.json.name == 'Type 455'
			response.status == HttpStatus.OK.value()
	}


	void 'Test excluding fields from reading PetType instance.'() {
		when: 'Read petType id excluded'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id')
		then: 'Should not return id'
			response.json.id == null
			response.status == HttpStatus.OK.value()
	}


	void 'Test including fields from reading PetType instance.'() {
		when: 'Read petType id excluded and then included'
			response = readDomainItemWithParams(domainId.toString(), 'excludes=id&includes=id')
		then: 'Should return id'
			response.json.id != null
			response.status == HttpStatus.OK.value()
	}


	void 'Test reading unexisting PetType instance.'() {
		when: 'Find unexisting petType'
			response = readDomainItemWithParams('9999999999', '')
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()
		when: 'Find unexisting petType id not a number'
			response = readDomainItemWithParams('nonexistent', '')
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}


	void 'Test updating PetType instance.'() {
		when: 'Update petType'
			response = sendUpdateWithData(domainId.toString()){
				name = 'Type 456'

			}
		then: 'Should return updated values'
			response.json.name == 'Type 456'

			response.status == HttpStatus.OK.value()
	}

	void 'Test updating unexisting PetType instance.'() {
		when: 'Update unexisting petType'
			response = sendUpdateWithData('9999999999'){
					name = 'Type 456'

			}
		then: 'Should not find'
			response.status == HttpStatus.NOT_FOUND.value()

		when: 'Update unexisting petType id not a number'
			response = sendUpdateWithData('nonexistent'){
					name = 'Type 456'

			}
		then: 'Should not find'
			response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
	}

	void 'Test PetType list sorting.'() {
		when: 'Get petType sorted list DESC'
			response = queryListWithParams('order=desc&sort=id')

		then: 'First item should be just inserted object'
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()

		when: 'Get petType sorted list ASC'
			response = queryListWithParams('order=asc&sort=id')

		then: 'First item should not be just inserted object'
			response.json[0].id != domainId
			response.status == HttpStatus.OK.value()
	}


	void 'Test PetType list max property query 2 items.'() {
		when: 'Get petType list with max 2 items'
			response = queryListWithParams('max=2')

		then: 'Should be only 2 items'
			response.json.size() == 2
	}


	 // have to have more then maxLimit items
	void 'Test PetType list max property.'() {
		given:
			int maxLimit = 100// Set real max items limit

		when: 'Get petType list without max param'
			response = queryListWithParams('')

		then: 'Should return default maximum items'
			response.json.size() == 10

		when: 'Get petType list with maximum items'
			response = queryListWithParams("max=$maxLimit")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit

		when: 'Get petType list with maximum + 1 items'
			response = queryListWithParams("max=${maxLimit+1}")

		then: 'Should contains maximum items'
			response.json.size() == maxLimit
	}


	void 'Test excluding fields in PetType list.'() {
		when: 'Get petType sorted list'
			response = queryListWithParams('excludes=id')

		then: 'First item should be just inserted object'
			response.json[0].id == null
	}


	void 'Test including fields in PetType list.'() {
		when: 'Get petType sorted list'
			response = queryListWithParams('excludes=id&includes=id')

		then: 'First item should be just inserted object'
			response.json[0].id != null
	}

	void 'Test querying in PetType list by dummy searchString.'() {
		when: 'Get petType list by searchString'
			response = queryListWithMap([searchString: "999999999999999"])

		then: 'Should be with size 0'
			response.json.size() == 0
			response.status == HttpStatus.OK.value()
	}

	void 'Test querying in PetType list by real searchString.'() {
		when: 'Get petType list by searchString'
			response = queryListWithMap(
					[order: 'desc', sort: 'id', searchString: "Type 456"])

		then: 'Should at least last inserted item'
			response.json.size() > 0
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	void 'Test filtering in PetType list by id.'() {
		when: 'Get petType list filtered by id'

			response = queryListWithMap([id: domainId])

		then: 'Should contains one item, just inserted item.'
			response.json.size() == 1
			response.json[0].id == domainId
			response.status == HttpStatus.OK.value()
	}

	@Unroll("PetType list search with props '#filter' returns '#respSize' items")
	void 'Filtering in PetType list by all properties.'() {
		given:
			response = queryListWithMap(filter)
			

		expect:
			response.json.size() == respSize
		where:
			filter 	        || respSize
			[:]                || 10
//Can't predict 'size'			[name:'Type 456'] || 1 

	}




	void "Test deleting other PetType instance."() {//This is for creating some data to test list sorting
		when: "Delete petType"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}


	void "Test deleting PetType instance."() {
		when: "Delete petType"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == HttpStatus.NO_CONTENT.value()
	}

}
