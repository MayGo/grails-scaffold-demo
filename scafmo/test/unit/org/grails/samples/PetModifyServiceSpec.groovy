package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PetModifyService)
@Mock(Pet)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PetModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Pet with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createPet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Pet with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createPet(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Pet with valid data returns Pet instance'() {
		setup:
			Map data = validData()
		when:
			Pet pet = service.createPet(data)
		then:
			pet != null
			pet.id != null
	}

	void 'Updating Pet without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updatePet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Pet with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updatePet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Pet with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updatePet(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Pet with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPet().id
		when:
			service.updatePet(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Pet with valid data returns Pet instance'() {

		setup:
			Map data = validData()
			data.id = createValidPet().id
		when:
			Pet pet = service.updatePet(data)
		then:
			pet != null
			pet.id == 1
	}

	void 'Deleting Pet without id is not possible'() {
		when:
			service.deletePet(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Pet with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deletePet(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Pet with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deletePet(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Pet is possible'() {
		setup:
			Long petId = createValidPet().id
			Pet pet = Pet.findById(petId).find()
		when:
			service.deletePet(petId)
		then:
			pet != null
			Pet.findById(petId) == null
	}

	Map invalidData() {

		return ['birthDate': null,
 'name': null,
 'type': null,
 'owner': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'birthDate':  new Date().clearTime(),
  'name':  'Pet 302',
  'owner':  [
    'id':  752,
    'version':  0,
    'firstName':  'firstName',
    'lastName':  'lastName',
    'telephone':  '555455'
  ],
  'type':  [
    'id':  null,
    'version':  null,
    'name':  'Type 452'
  ]
]
		return data
	}

	Pet createValidPet() {
		Pet pet = new Pet(validData())
		pet.save flush: true, failOnError: true
		return pet
	}

}

