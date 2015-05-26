package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(VetModifyService)
@Mock(Vet)
@SuppressWarnings(['DuplicateNumberLiteral'])
class VetModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

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
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateVet(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Vet with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateVet(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Vet with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidVet().id
		when:
			service.updateVet(data)
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

	void 'Deleting Vet without id is not possible'() {
		when:
			service.deleteVet(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Vet with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteVet(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Vet with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteVet(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Vet is possible'() {
		setup:
			Long vetId = createValidVet().id
			Vet vet = Vet.findById(vetId).find()
		when:
			service.deleteVet(vetId)
		then:
			vet != null
			Vet.findById(vetId) == null
	}

	Map invalidData() {

		return ['firstName': null,
 'lastName': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'firstName':  'firstName',
  'lastName':  'lastName'
]
		return data
	}

	Vet createValidVet() {
		Vet vet = new Vet(validData())
		vet.save flush: true, failOnError: true
		return vet
	}

}

