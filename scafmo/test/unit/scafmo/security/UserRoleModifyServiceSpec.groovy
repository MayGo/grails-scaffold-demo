package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(UserRoleModifyService)
@Mock(UserRole)
class UserRoleModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateUserRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating UserRole with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateUserRole(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating UserRole with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidUserRole().id
		when:
			UserRole userRole = service.updateUserRole(data)
		then:
			thrown(ValidationException)
	}

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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"role":["id":null,"version":null],"user":["id":null,"version":null]]

		return data
	}

	UserRole createValidUserRole(){
		UserRole userRole = new UserRole(validData())
		userRole.save flush:true, failOnError: true
		return userRole
	}

}

