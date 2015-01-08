package scafmo.constr


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class TestOtherSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/testothers"
	
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
	
	void "Test creating another TestOther instance."() {//This is for creating some data to test list sorting
		when: "Create testOther"
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = '2015-01-08 10:10:39.609+0200'
				testEnum = 'TEST_1'
				testStringType = 1

			}
			
			otherDomainId = response.json.id
			
			
		then: "Should create and return created values"
			response.json.booleanNullable == true
			response.json.testDate == '2015-01-08T08:10:39Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1

			response.status == CREATED.value()
	}

	void "Test creating TestOther instance."() {
		when: "Create testOther"
			response = sendCreateWithData(){
				booleanNullable = true
				testDate = '2015-01-08 10:10:39.618+0200'
				testEnum = 'TEST_1'
				testStringType = 1

			}
			
			domainId = response.json.id
			
			
		then: "Should create and return created values"
			
			response.json.booleanNullable == true
			response.json.testDate == '2015-01-08T08:10:39Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1

			response.status == CREATED.value()
	}
	
	
			
		

	void "Test reading TestOther instance."() {
		when: "Read testOther"
			response = readDomainItemWithParams(domainId.toString(), "")
		then: "Should return correct values"
			
			response.json.booleanNullable == true
			response.json.testDate == '2015-01-08T08:10:39Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1

			response.status == OK.value()
	}
	
	
	void "Test excluding fields from reading TestOther instance."() {
		when: "Read testOther id excluded"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id")
		then: "Should not return id"
			response.json.id == null
			response.status == OK.value()
	}
	
	
	void "Test including fields from reading TestOther instance."() {
		when: "Read testOther id excluded and then included"
			response = readDomainItemWithParams(domainId.toString(), "excludes=id&includes=id")
		then: "Should return id"
			response.json.id != null
			response.status == OK.value()
	}
	
	
	void "Test reading unexisting TestOther instance."() {
		when:"Find unexisting testOther"
			response = readDomainItemWithParams("9999999999", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
		when:"Find unexisting testOther id not a number"
			response = readDomainItemWithParams("nonexistent", "")
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}

	
	void "Test updating TestOther instance."() {
		when: "Update testOther"
			response = sendUpdateWithData(domainId.toString()){
				booleanNullable = true
				testDate = '2015-01-08 10:10:39.620+0200'
				testEnum = 'TEST_1'
				testStringType = 1


			}
		then: "Should return updated values"
			response.json.booleanNullable == true
			response.json.testDate == '2015-01-08T08:10:39Z'
			response.json.testEnum == 'TEST_1'
			response.json.testStringType?.id == 1


			response.status == OK.value()
	}

	void "Test updating unexisting TestOther instance."() {
		when: "Update unexisting testOther"
			response = sendUpdateWithData("9999999999"){
					booleanNullable = true
				testDate = '2015-01-08 10:10:39.620+0200'
				testEnum = 'TEST_1'
				testStringType = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
			
		when: "Update unexisting testOther id not a number"
			response = sendUpdateWithData("nonexistent"){
					booleanNullable = true
				testDate = '2015-01-08 10:10:39.620+0200'
				testEnum = 'TEST_1'
				testStringType = 1


			}
		then:"Should not find"
			response.status == NOT_FOUND.value()
	}
	
	void "Test TestOther list sorting."() {
		when:"Get testOther sorted list DESC"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
		
		when:"Get testOther sorted list ASC"
			response = queryListWithParams("order=asc&sort=id")

		then:"First item should not be just inserted object"
			response.json[0].id != domainId
			response.status == OK.value()
	}
	
	
	void "Test TestOther list max property."() {
		when:"Get testOther list with max 2 items"
			response = queryListWithParams("max=2")

		then:"Should be only 2 items"
			response.json.size() == 2
	}
	
	@Ignore // have to have more then maxLimit items
	void "Test TestOther list max property."() {
		given:
			int maxLimit = 100// Set real max items limit
			
		when:"Get testOther list with maximum items"
			response = queryListWithParams("max=$maxLimit")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
			
		when:"Get testOther list with maximum + 1 items"
			response = queryListWithParams("max=${maxLimit+1}")

		then:"Should contains maximum items"
			response.json.size() == maxLimit
	}
	
	
	void "Test excluding fields in TestOther list."() {
		when:"Get testOther sorted list"
			response = queryListWithParams("excludes=id")

		then:"First item should be just inserted object"
			response.json[0].id == null
	}
	
	
	void "Test including fields in TestOther list."() {
		when:"Get testOther sorted list"
			response = queryListWithParams("excludes=id&includes=id")

		then:"First item should be just inserted object"
			response.json[0].id != null
	}
	
	void "Test filtering in TestOther list."() {
		when:"Get testOther sorted list"
			response = queryListWithParams("order=desc&sort=id")

		then:"First item should be just inserted object"
			response.json[0].id == domainId
			response.status == OK.value()
	}
	
	
	void "Test deleting other TestOther instance."() {//This is for creating some data to test list sorting
		when: "Delete testOther"
			response = deleteDomainItem(otherDomainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}
	
	
	void "Test deleting TestOther instance."() {
		when: "Delete testOther"
			response = deleteDomainItem(domainId.toString())
		then:
			response.status == NO_CONTENT.value()
	}

}
