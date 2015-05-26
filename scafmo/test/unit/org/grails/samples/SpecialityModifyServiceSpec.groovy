package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(SpecialityModifyService)
@Mock(Speciality)
@SuppressWarnings(['DuplicateNumberLiteral'])
class SpecialityModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Speciality with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createSpeciality(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Speciality with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createSpeciality(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Speciality with valid data returns Speciality instance'() {
		setup:
			Map data = validData()
		when:
			Speciality speciality = service.createSpeciality(data)
		then:
			speciality != null
			speciality.id != null
	}

	void 'Updating Speciality without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateSpeciality(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Speciality with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateSpeciality(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Speciality with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateSpeciality(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Speciality with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidSpeciality().id
		when:
			service.updateSpeciality(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Speciality with valid data returns Speciality instance'() {

		setup:
			Map data = validData()
			data.id = createValidSpeciality().id
		when:
			Speciality speciality = service.updateSpeciality(data)
		then:
			speciality != null
			speciality.id == 1
	}

	void 'Deleting Speciality without id is not possible'() {
		when:
			service.deleteSpeciality(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Speciality with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteSpeciality(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Speciality with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteSpeciality(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Speciality is possible'() {
		setup:
			Long specialityId = createValidSpeciality().id
			Speciality speciality = Speciality.findById(specialityId).find()
		when:
			service.deleteSpeciality(specialityId)
		then:
			speciality != null
			Speciality.findById(specialityId) == null
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

