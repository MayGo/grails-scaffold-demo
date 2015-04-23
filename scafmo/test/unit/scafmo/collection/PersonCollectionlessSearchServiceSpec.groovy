package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(PersonCollectionlessSearchService)
@Mock(PersonCollectionless)
class PersonCollectionlessSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering PersonCollectionless without id is not possible'() {

		when:
			service.queryForPersonCollectionless(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PersonCollectionless with illegal id is not possible'() {
		when:
			service.queryForPersonCollectionless(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PersonCollectionless with fictional id is not possible'() {
		when:
			service.queryForPersonCollectionless(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering PersonCollectionless with valid id returns PersonCollectionless instance'() {

		setup:
			Long personCollectionlessId = createValidPersonCollectionless().id
		when:
			PersonCollectionless personCollectionless = service.queryForPersonCollectionless(personCollectionlessId)
		then:
			personCollectionless != null
			personCollectionless.id == 1
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'age':  456,
  'name':  'John454 Doe455'
]
		return data
	}

	PersonCollectionless createValidPersonCollectionless() {
		PersonCollectionless personCollectionless = new PersonCollectionless(validData())
		personCollectionless.save flush: true, failOnError: true
		return personCollectionless
	}
}

