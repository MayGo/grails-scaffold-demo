package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonModifyService)
@Mock(Person)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PersonModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

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
			Map data = [id: ILLEGAL_ID]
		when:
			service.updatePerson(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Person with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updatePerson(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Person with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPerson().id
		when:
			service.updatePerson(data)
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

	void 'Deleting Person without id is not possible'() {
		when:
			service.deletePerson(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Person with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deletePerson(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Person with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deletePerson(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Person is possible'() {
		setup:
			Long personId = createValidPerson().id
			Person person = Person.findById(personId).find()
		when:
			service.deletePerson(personId)
		then:
			person != null
			Person.findById(personId) == null
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

	Person createValidPerson() {
		Person person = new Person(validData())
		person.save flush: true, failOnError: true
		return person
	}

}

