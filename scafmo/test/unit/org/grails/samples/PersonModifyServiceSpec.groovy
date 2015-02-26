package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonModifyService)
@Mock(Person)
class PersonModifyServiceSpec extends Specification {

	void 'Creating Person with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createPerson(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Person with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createPerson(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Person with valid data returns Person instance'() {

		setup:
			Map data = validData()
		when:
			Person person = service.createPerson(data)
		then:
			person != null
			person.id != null
	}

	void 'Updating Person without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updatePerson(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Person with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updatePerson(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Person with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updatePerson(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Person with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPerson().id
		when:
			Person person = service.updatePerson(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Person with valid data returns Person instance'() {

		setup:
			Map data = validData()
			data.id = createValidPerson().id
		when:
			Person person = service.updatePerson(data)
		then:
			person != null
			person.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"firstName":"firstName","lastName":"lastName"]

		return data
	}

	Person createValidPerson(){
		Person person = new Person(validData())
		person.save flush:true, failOnError: true
		return person
	}

}

