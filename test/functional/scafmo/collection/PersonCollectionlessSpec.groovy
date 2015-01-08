package scafmo.collection


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class PersonCollectionlessSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/personcollectionlesss"
	
	@Shared
	Long domainId
	@Shared
	Long otherDomainId
	
	@Shared
	def authResponse
	
	@Shared
	def response
	
	def setupSpec() {
		authResponse = sendCorrectCredentials()
	}
	
	void "Test creating another PersonCollectionless instance."() {//This is for creating some data to test list sorting
		when: "Create personCollectionless"
			response = sendCreateWithData(){
				age = 306
				name = 'John304 Doe305'
				division = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.age == 306
			response.json.name == 'John304 Doe305'
			response.json.division?.id == 1

			response.status == CREATED.value()
	}

	void "Test creating PersonCollectionless instance."() {
		when: "Create personCollectionless"
			response = sendCreateWithData(){
				age = 309
				name = 'John307 Doe308'
				division = 1

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.age == 309
			response.json.name == 'John307 Doe308'
			response.json.division?.id == 1

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading PersonCollectionless instance."() {
		when: "Read personCollectionless"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.age == 309
			response.json.name == 'John307 Doe308'
			response.json.division?.id == 1

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading PersonCollectionless instance."() {
		when: "Read personCollectionless id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading PersonCollectionless instance."() {
		when: "Read personCollectionless id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting PersonCollectionless instance."() {
		when:"Find unexisting personCollectionless"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting personCollectionless id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating PersonCollectionless instance."() {
		when: "Update personCollectionless"
			response = sendUpdateWithData(domainId.toString()){
				age = 312
				name = 'John310 Doe311'
				division = 1


			}
		then: "Should return updated values"
			response.json.age == 312
			response.json.name == 'John310 Doe311'
			response.json.division?.id == 1


			response.status == OK.value()
	}

	void "Test updating unexisting PersonCollectionless instance."() {
		when: "Update unexisting personCollectionless"
			response = sendUpdateWithData("9999999999"){
					age = 312
				name = 'John310 Doe311'
				division = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting personCollectionless id not a number"
			response = sendUpdateWithData("nonexistent"){
					age = 312
				name = 'John310 Doe311'
				division = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test PersonCollectionless list sorting."() {
		when:"Get personCollectionless sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get personCollectionless sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test PersonCollectionless list max property."() {
		when:"Get personCollectionless list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	
	 // have to have more then maxLimit items
	void "Test PersonCollectionless list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get personCollectionless list without max param"
			response = queryListWithParams("")

		then:"Should return default maximum items"
			response.json.size() == 10
			
		when:"Get personCollectionless list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get personCollectionless list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in PersonCollectionless list."() {
		when:"Get personCollectionless sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in PersonCollectionless list."() {
		when:"Get personCollectionless sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in PersonCollectionless list by id."() {
		when:"Get personCollectionless list filtered by id"

			response = queryListWithUrlVariables("filter={filter}", [filter:"{id:${domainId}}"])

		then:"Should contains one item, just inserted item."
			response.json[0].id == domainId
			response.json.size() == 1
			response.status == OK.value()
	}
	
	void "Test filtering in PersonCollectionless list by all properties."() {
		given:
			response = queryListWithUrlVariables("filter={filter}", [filter:"${jsonVal}"])
			
			
		expect:
			response.json.size() == respSize
		where:
			jsonVal 	        || respSize
			"{}"                || 10
	
			"""{"age":312}"""     		|| 1

	
		//Can't predict 'size'	"""{"name":"John310 Doe311"}"""     		|| 1

	
	}
	
	
	
	
	void "Test deleting other PersonCollectionless instance."() {//This is for creating some data to test list sorting
		when: "Delete personCollectionless"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting PersonCollectionless instance."() {
		when: "Delete personCollectionless"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
