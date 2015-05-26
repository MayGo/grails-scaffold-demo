package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PetTypeModifyService)
@Mock(PetType)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PetTypeModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating PetType with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createPetType(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating PetType with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createPetType(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating PetType with valid data returns PetType instance'() {
		setup:
			Map data = validData()
		when:
			PetType petType = service.createPetType(data)
		then:
			petType != null
			petType.id != null
	}

	void 'Updating PetType without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updatePetType(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PetType with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updatePetType(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PetType with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updatePetType(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating PetType with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPetType().id
		when:
			service.updatePetType(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating PetType with valid data returns PetType instance'() {

		setup:
			Map data = validData()
			data.id = createValidPetType().id
		when:
			PetType petType = service.updatePetType(data)
		then:
			petType != null
			petType.id == 1
	}

	void 'Deleting PetType without id is not possible'() {
		when:
			service.deletePetType(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PetType with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deletePetType(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PetType with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deletePetType(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved PetType is possible'() {
		setup:
			Long petTypeId = createValidPetType().id
			PetType petType = PetType.findById(petTypeId).find()
		when:
			service.deletePetType(petTypeId)
		then:
			petType != null
			PetType.findById(petTypeId) == null
	}

	Map invalidData() {

		return ['name': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Type 455'
]
		return data
	}

	PetType createValidPetType() {
		PetType petType = new PetType(validData())
		petType.save flush: true, failOnError: true
		return petType
	}

}

