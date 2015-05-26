package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(OwnerModifyService)
@Mock(Owner)
@SuppressWarnings(['DuplicateNumberLiteral'])
class OwnerModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Owner with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Owner with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createOwner(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Owner with valid data returns Owner instance'() {
		setup:
			Map data = validData()
		when:
			Owner owner = service.createOwner(data)
		then:
			owner != null
			owner.id != null
	}

	void 'Updating Owner without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Owner with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Owner with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateOwner(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Owner with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidOwner().id
		when:
			service.updateOwner(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Owner with valid data returns Owner instance'() {

		setup:
			Map data = validData()
			data.id = createValidOwner().id
		when:
			Owner owner = service.updateOwner(data)
		then:
			owner != null
			owner.id == 1
	}

	void 'Deleting Owner without id is not possible'() {
		when:
			service.deleteOwner(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Owner with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteOwner(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Owner with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteOwner(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Owner is possible'() {
		setup:
			Long ownerId = createValidOwner().id
			Owner owner = Owner.findById(ownerId).find()
		when:
			service.deleteOwner(ownerId)
		then:
			owner != null
			Owner.findById(ownerId) == null
	}

	Map invalidData() {

		return ['address': null,
 'city': null,
 'firstName': null,
 'lastName': null,
 'telephone': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'address':  'address',
  'city':  'city',
  'firstName':  'firstName',
  'lastName':  'lastName',
  'telephone':  '555452'
]
		return data
	}

	Owner createValidOwner() {
		Owner owner = new Owner(validData())
		owner.save flush: true, failOnError: true
		return owner
	}

}

