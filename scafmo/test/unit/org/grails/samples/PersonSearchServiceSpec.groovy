package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(PersonSearchService)
@Mock(Person)
class PersonSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Person without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Person with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Person with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Person with valid id returns Person instance'() {

		setup:
			Long personId = createValidPerson().id
		when:
			Person person = service.queryForRead(personId)
		then:
			person != null
			person.id == 1
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

