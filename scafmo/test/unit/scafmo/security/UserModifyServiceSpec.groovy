package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(UserModifyService)
@Mock(User)
@SuppressWarnings(['DuplicateNumberLiteral'])
class UserModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating User with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createUser(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating User with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createUser(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating User with valid data returns User instance'() {
		setup:
			Map data = validData()
		when:
			User user = service.createUser(data)
		then:
			user != null
			user.id != null
	}

	void 'Updating User without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateUser(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating User with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateUser(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating User with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateUser(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating User with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidUser().id
		when:
			service.updateUser(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating User with valid data returns User instance'() {

		setup:
			Map data = validData()
			data.id = createValidUser().id
		when:
			User user = service.updateUser(data)
		then:
			user != null
			user.id == 1
	}

	void 'Deleting User without id is not possible'() {
		when:
			service.deleteUser(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting User with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteUser(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting User with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteUser(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved User is possible'() {
		setup:
			Long userId = createValidUser().id
			User user = User.findById(userId).find()
		when:
			service.deleteUser(userId)
		then:
			user != null
			User.findById(userId) == null
	}

	Map invalidData() {

		return ['username': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'enabled':  true,
  'username':  'John Doe 302'
]
		return data
	}

	User createValidUser() {
		User user = new User(validData())
		user.save flush: true, failOnError: true
		return user
	}

}

