package scafmo.security


import grails.plugins.rest.client.*
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore
import static org.springframework.http.HttpStatus.*
import defpackage.AbstractRestSpec
import defpackage.RestQueries




class UserRoleSpec extends AbstractRestSpec implements RestQueries{
	
	String REST_URL = "${baseUrl}/userroles"
	
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
	
	void "Test creating another UserRole instance."() {//This is for creating some data to test list sorting
		when: "Create userRole"
			response = sendCreateWithData(){
				role = null
				user = null

			}
			otherDomainId = response.json.id
			
		then: "Should create and return created values"
		
			response.json.role?.id == null
			response.json.user?.id == null

			response.status == CREATED.value()
	}

	void "Test creating UserRole instance."() {
		when: "Create userRole"
			response = sendCreateWithData(){
				role = null
				user = null

			}
			domainId = response.json.id
			
		then: "Should create and return created values"
			response.json.role?.id == null
			response.json.user?.id == null

			response.status == CREATED.value()
	}
	
	
			
		

}
