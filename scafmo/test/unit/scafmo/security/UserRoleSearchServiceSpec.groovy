package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(UserRoleSearchService)
@Mock(UserRole)
class UserRoleSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering UserRole without id is not possible'() {

		when:
			service.queryForUserRole(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering UserRole with illegal id is not possible'() {
		when:
			service.queryForUserRole(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering UserRole with fictional id is not possible'() {
		when:
			service.queryForUserRole(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}
	@Ignore
	void 'Quering UserRole with valid id returns UserRole instance'() {

		setup:
			Long userRoleId = createValidUserRole().id
		when:
			UserRole userRole = service.queryForUserRole(userRoleId)
		then:
			userRole != null
			userRole.id == 1
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'role':  [
    'id':  null,
    'version':  null
  ],
  'user':  [
    'id':  null,
    'version':  null
  ]
]
		return data
	}

	UserRole createValidUserRole() {
		UserRole userRole = new UserRole(validData())
		userRole.save flush: true, failOnError: true
		return userRole
	}
}

