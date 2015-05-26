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
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering UserRole with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering UserRole with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}
	@Ignore
	void 'Quering UserRole with valid id returns UserRole instance'() {

		setup:
			Long userRoleId = createValidUserRole().id
		when:
			UserRole userRole = service.queryForRead(userRoleId)
		then:
			userRole != null
			userRole.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
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

