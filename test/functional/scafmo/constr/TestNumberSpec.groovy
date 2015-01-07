package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class TestNumberSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/testnumbers"
	
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
	
	void "Test creating another TestNumber instance."() {//This is for creating some data to test list sorting
		when: "Create testNumber"
			response = sendCreateWithData(){
				doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0

			}
			otherDomainId = response.json.id
			
		then: "Should create and return created values"
		
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0

			response.status == CREATED.value()
	}

	void "Test creating TestNumber instance."() {
		when: "Create testNumber"
			response = sendCreateWithData(){
				doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0

			}
			domainId = response.json.id
			
		then: "Should create and return created values"
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading TestNumber instance."() {
		when: "Read testNumber"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading TestNumber instance."() {
		when: "Read testNumber id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading TestNumber instance."() {
		when: "Read testNumber id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting TestNumber instance."() {
		when:"Find unexisting testNumber"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting testNumber id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating TestNumber instance."() {
		when: "Update testNumber"
			response = sendUpdateWithData(domainId.toString()){
				doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0


			}
		then: "Should return updated values"
			response.json.doubleNr == 0.0
			response.json.floatNr == 0.0
			response.json.floatNrScale == 0.0
			response.json.integerNr == 0
			response.json.integerNrInList == 1
			response.json.integerNrMax == 0
			response.json.integerNrMin == 2
			response.json.integerNrNotEqual == 0
			response.json.integerNrRange == 18
			response.json.integerNrUnique == 0
			response.json.longNr == 0


			response.status == OK.value()
	}

	void "Test updating unexisting TestNumber instance."() {
		when: "Update unexisting testNumber"
			response = sendUpdateWithData("9999999999"){
					doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting testNumber id not a number"
			response = sendUpdateWithData("nonexistent"){
					doubleNr = 0.0
				floatNr = 0.0
				floatNrScale = 0.0
				integerNr = 0
				integerNrInList = 1
				integerNrMax = 0
				integerNrMin = 2
				integerNrNotEqual = 0
				integerNrRange = 18
				integerNrUnique = 0
				longNr = 0


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test TestNumber list sorting."() {
		when:"Get testNumber sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get testNumber sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test TestNumber list max property."() {
		when:"Get testNumber list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test TestNumber list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get testNumber list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get testNumber list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in TestNumber list."() {
		when:"Get testNumber sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in TestNumber list."() {
		when:"Get testNumber sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in TestNumber list."() {
		when:"Get testNumber sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other TestNumber instance."() {//This is for creating some data to test list sorting
		when: "Delete testNumber"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting TestNumber instance."() {
		when: "Delete testNumber"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
