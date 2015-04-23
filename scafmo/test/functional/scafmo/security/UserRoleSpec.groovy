package scafmo.security

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import defpackage.AuthQueries
import defpackage.TestUtils
import spock.lang.Specification
import spock.lang.Unroll

class UserRoleSpec extends Specification implements RestQueries, AuthQueries, TestUtils{

	String REST_URL = "${APP_URL}/userroles/v1"

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
