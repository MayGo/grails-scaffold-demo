package scafmo.security

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class UserRoleSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/userroles"
	
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

}
