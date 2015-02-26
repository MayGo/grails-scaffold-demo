package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(VetModifyService)
@Mock(Vet)
class VetModifyServiceSpec extends Specification {

	void 'Creating Vet with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createVet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Vet with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createVet(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Vet with valid data returns Vet instance'() {

		setup:
			Map data = validData()
		when:
			Vet vet = service.createVet(data)
		then:
			vet != null
			vet.id != null
	}

	void 'Updating Vet without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateVet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Vet with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updateVet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Vet with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateVet(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Vet with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidVet().id
		when:
			Vet vet = service.updateVet(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Vet with valid data returns Vet instance'() {

		setup:
			Map data = validData()
			data.id = createValidVet().id
		when:
			Vet vet = service.updateVet(data)
		then:
			vet != null
			vet.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"firstName":"firstName","lastName":"lastName"]

		return data
	}

	Vet createValidVet(){
		Vet vet = new Vet(validData())
		vet.save flush:true, failOnError: true
		return vet
	}

}

