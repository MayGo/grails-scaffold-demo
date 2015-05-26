package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(DivisionCollectionlessSearchService)
@Mock(DivisionCollectionless)
class DivisionCollectionlessSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering DivisionCollectionless without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering DivisionCollectionless with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering DivisionCollectionless with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering DivisionCollectionless with valid id returns DivisionCollectionless instance'() {

		setup:
			Long divisionCollectionlessId = createValidDivisionCollectionless().id
		when:
			DivisionCollectionless divisionCollectionless = service.queryForRead(divisionCollectionlessId)
		then:
			divisionCollectionless != null
			divisionCollectionless.id == 1
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

	DivisionCollectionless createValidDivisionCollectionless() {
		DivisionCollectionless divisionCollectionless = new DivisionCollectionless(validData())
		divisionCollectionless.save flush: true, failOnError: true
		return divisionCollectionless
	}
}

