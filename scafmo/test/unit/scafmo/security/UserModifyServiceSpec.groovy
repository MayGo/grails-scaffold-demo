package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(UserModifyService)
@Mock(User)
class UserModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateUser(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating User with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateUser(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating User with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidUser().id
		when:
			User user = service.updateUser(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"accountExpired":false,"accountLocked":false,"enabled":true,"passwordExpired":false,"username":"John Doe 302"]

		return data
	}

	User createValidUser(){
		User user = new User(validData())
		user.save flush:true, failOnError: true
		return user
	}

}

