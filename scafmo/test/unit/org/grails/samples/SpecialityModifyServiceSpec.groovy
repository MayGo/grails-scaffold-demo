package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(SpecialityModifyService)
@Mock(Speciality)
class SpecialityModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateSpeciality(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Speciality with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateSpeciality(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Speciality with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidSpeciality().id
		when:
			Speciality speciality = service.updateSpeciality(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"name":"Speciality 152"]

		return data
	}

	Speciality createValidSpeciality(){
		Speciality speciality = new Speciality(validData())
		speciality.save flush:true, failOnError: true
		return speciality
	}

}

