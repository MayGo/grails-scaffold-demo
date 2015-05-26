package scafmo.security

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(RoleModifyService)
@Mock(Role)
@SuppressWarnings(['DuplicateNumberLiteral'])
class RoleModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

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
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateRole(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Role with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateRole(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Role with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidRole().id
		when:
			service.updateRole(data)
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

	void 'Deleting Role without id is not possible'() {
		when:
			service.deleteRole(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Role with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteRole(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Role with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteRole(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Role is possible'() {
		setup:
			Long roleId = createValidRole().id
			Role role = Role.findById(roleId).find()
		when:
			service.deleteRole(roleId)
		then:
			role != null
			Role.findById(roleId) == null
	}

	Map invalidData() {

		return ['authority': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'authority':  'ROLE_302'
]
		return data
	}

	Role createValidRole() {
		Role role = new Role(validData())
		role.save flush: true, failOnError: true
		return role
	}

}

