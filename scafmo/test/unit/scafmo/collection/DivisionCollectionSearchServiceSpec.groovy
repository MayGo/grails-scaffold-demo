package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(DivisionCollectionSearchService)
@Mock(DivisionCollection)
class DivisionCollectionSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering DivisionCollection without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering DivisionCollection with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering DivisionCollection with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering DivisionCollection with valid id returns DivisionCollection instance'() {

		setup:
			Long divisionCollectionId = createValidDivisionCollection().id
		when:
			DivisionCollection divisionCollection = service.queryForRead(divisionCollectionId)
		then:
			divisionCollection != null
			divisionCollection.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Division152'
]
		return data
	}

	DivisionCollection createValidDivisionCollection() {
		DivisionCollection divisionCollection = new DivisionCollection(validData())
		divisionCollection.save flush: true, failOnError: true
		return divisionCollection
	}
}

