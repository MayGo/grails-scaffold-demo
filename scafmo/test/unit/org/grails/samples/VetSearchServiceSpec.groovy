package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(VetSearchService)
@Mock(Vet)
class VetSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Vet without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Vet with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Vet with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Vet with valid id returns Vet instance'() {

		setup:
			Long vetId = createValidVet().id
		when:
			Vet vet = service.queryForRead(vetId)
		then:
			vet != null
			vet.id == 1
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

