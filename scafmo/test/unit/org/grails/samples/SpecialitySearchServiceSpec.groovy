package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(SpecialitySearchService)
@Mock(Speciality)
class SpecialitySearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Speciality without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Speciality with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Speciality with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Speciality with valid id returns Speciality instance'() {

		setup:
			Long specialityId = createValidSpeciality().id
		when:
			Speciality speciality = service.queryForRead(specialityId)
		then:
			speciality != null
			speciality.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Speciality 152'
]
		return data
	}

	Speciality createValidSpeciality() {
		Speciality speciality = new Speciality(validData())
		speciality.save flush: true, failOnError: true
		return speciality
	}
}

