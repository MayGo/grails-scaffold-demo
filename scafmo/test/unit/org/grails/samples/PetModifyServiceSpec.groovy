package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PetModifyService)
@Mock(Pet)
class PetModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updatePet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Pet with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updatePet(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Pet with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPet().id
		when:
			Pet pet = service.updatePet(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"birthDate":"2015-02-25T22:00:00Z","name":"Pet 302","owner":["id":752,"version":0],"type":["id":null,"version":null]]

		return data
	}

	Pet createValidPet(){
		Pet pet = new Pet(validData())
		pet.save flush:true, failOnError: true
		return pet
	}

}

