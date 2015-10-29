package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(UserRoleModifyService)
@Mock(UserRole)
@SuppressWarnings(['DuplicateNumberLiteral'])
class UserRoleModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating UserRole with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createUserRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating UserRole with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createUserRole(data)
		then:
			thrown(ValidationException)
	}
	@Ignore
	void 'Creating UserRole with valid data returns UserRole instance'() {
		setup:
			Map data = validData()
		when:
			UserRole userRole = service.createUserRole(data)
		then:
			userRole != null
			userRole.id != null
	}

	void 'Updating UserRole without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateUserRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating UserRole with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateUserRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating UserRole with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateUserRole(data)
		then:
			thrown(ResourceNotFound)
	}
	@Ignore
	void 'Updating UserRole with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidUserRole().id
		when:
			service.updateUserRole(data)
		then:
			thrown(ValidationException)
	}
	@Ignore
	void 'Updating UserRole with valid data returns UserRole instance'() {

		setup:
			Map data = validData()
			data.id = createValidUserRole().id
		when:
			UserRole userRole = service.updateUserRole(data)
		then:
			userRole != null
			userRole.id == 1
	}

	void 'Deleting UserRole without id is not possible'() {
		when:
			service.deleteUserRole(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting UserRole with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteUserRole(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting UserRole with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteUserRole(id)
		then:
			thrown(ResourceNotFound)
	}
	@Ignore
	void 'Deleting saved UserRole is possible'() {
		setup:
			Long userRoleId = createValidUserRole().id
			UserRole userRole = UserRole.findById(userRoleId).find()
		when:
			service.deleteUserRole(userRoleId)
		then:
			userRole != null
			UserRole.findById(userRoleId) == null
	}

	Map invalidData() {

		return ['role': null,
 'user': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'role':  [
    'id':  null,
    'version':  null,
    'authority':  'ROLE_304'
  ],
  'user':  [
    'id':  null,
    'version':  null,
    'enabled':  true,
    'passwordExpired':  false,
    'username':  'John Doe 304'
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

