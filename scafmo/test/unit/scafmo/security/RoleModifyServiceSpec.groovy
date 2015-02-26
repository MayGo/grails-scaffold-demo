package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(RoleModifyService)
@Mock(Role)
class RoleModifyServiceSpec extends Specification {

	void 'Creating Role with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Role with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createRole(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Role with valid data returns Role instance'() {

		setup:
			Map data = validData()
		when:
			Role role = service.createRole(data)
		then:
			role != null
			role.id != null
	}

	void 'Updating Role without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Role with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updateRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Role with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateRole(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Role with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidRole().id
		when:
			Role role = service.updateRole(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Role with valid data returns Role instance'() {

		setup:
			Map data = validData()
			data.id = createValidRole().id
		when:
			Role role = service.updateRole(data)
		then:
			role != null
			role.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"authority":"ROLE_302"]

		return data
	}

	Role createValidRole(){
		Role role = new Role(validData())
		role.save flush:true, failOnError: true
		return role
	}

}

