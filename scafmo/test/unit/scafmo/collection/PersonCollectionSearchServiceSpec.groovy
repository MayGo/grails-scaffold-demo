package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(PersonCollectionSearchService)
@Mock(PersonCollection)
class PersonCollectionSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering PersonCollection without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PersonCollection with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering PersonCollection with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering PersonCollection with valid id returns PersonCollection instance'() {

		setup:
			Long personCollectionId = createValidPersonCollection().id
		when:
			PersonCollection personCollection = service.queryForRead(personCollectionId)
		then:
			personCollection != null
			personCollection.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'age':  456,
  'name':  'John454 Doe455'
]
		return data
	}

	PersonCollection createValidPersonCollection() {
		PersonCollection personCollection = new PersonCollection(validData())
		personCollection.save flush: true, failOnError: true
		return personCollection
	}
}

