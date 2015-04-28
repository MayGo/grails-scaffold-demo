package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(PetSearchService)
@Mock(Pet)
class PetSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Pet without id is not possible'() {

		when:
			service.queryForPet(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Pet with illegal id is not possible'() {
		when:
			service.queryForPet(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Pet with fictional id is not possible'() {
		when:
			service.queryForPet(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Pet with valid id returns Pet instance'() {

		setup:
			Long petId = createValidPet().id
		when:
			Pet pet = service.queryForPet(petId)
		then:
			pet != null
			pet.id == 1
	}

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

