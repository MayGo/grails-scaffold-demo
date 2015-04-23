package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(PetTypeSearchService)
@Mock(PetType)
class PetTypeSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering PetType without id is not possible'() {

		when:
			service.queryForPetType(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PetType with illegal id is not possible'() {
		when:
			service.queryForPetType(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PetType with fictional id is not possible'() {
		when:
			service.queryForPetType(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering PetType with valid id returns PetType instance'() {

		setup:
			Long petTypeId = createValidPetType().id
		when:
			PetType petType = service.queryForPetType(petTypeId)
		then:
			petType != null
			petType.id == 1
	}

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

