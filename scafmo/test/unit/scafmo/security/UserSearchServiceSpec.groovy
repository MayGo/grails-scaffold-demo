package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(UserSearchService)
@Mock(User)
class UserSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering User without id is not possible'() {

		when:
			service.queryForUser(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering User with illegal id is not possible'() {
		when:
			service.queryForUser(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering User with fictional id is not possible'() {
		when:
			service.queryForUser(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering User with valid id returns User instance'() {

		setup:
			Long userId = createValidUser().id
		when:
			User user = service.queryForUser(userId)
		then:
			user != null
			user.id == 1
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'accountExpired':  false,
  'accountLocked':  false,
  'enabled':  true,
  'passwordExpired':  false,
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

