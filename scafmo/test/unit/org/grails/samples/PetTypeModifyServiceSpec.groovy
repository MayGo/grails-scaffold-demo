package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PetTypeModifyService)
@Mock(PetType)
class PetTypeModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updatePetType(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PetType with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updatePetType(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating PetType with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPetType().id
		when:
			PetType petType = service.updatePetType(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"name":"Type 455"]

		return data
	}

	PetType createValidPetType(){
		PetType petType = new PetType(validData())
		petType.save flush:true, failOnError: true
		return petType
	}

}

