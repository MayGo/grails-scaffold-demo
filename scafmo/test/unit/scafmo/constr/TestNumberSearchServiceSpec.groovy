package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(TestNumberSearchService)
@Mock(TestNumber)
class TestNumberSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering TestNumber without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestNumber with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestNumber with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering TestNumber with valid id returns TestNumber instance'() {

		setup:
			Long testNumberId = createValidTestNumber().id
		when:
			TestNumber testNumber = service.queryForRead(testNumberId)
		then:
			testNumber != null
			testNumber.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'doubleNr':  123.123,
  'floatNr':  123.123,
  'floatNrScale':  2.34,
  'integerNr':  303,
  'integerNrInList':  3,
  'integerNrMax':  2,
  'integerNrMin':  3,
  'integerNrNotEqual':  2,
  'integerNrRange':  19,
  'integerNrUnique':  304,
  'longNr':  4
]
		return data
	}

	TestNumber createValidTestNumber() {
		TestNumber testNumber = new TestNumber(validData())
		testNumber.save flush: true, failOnError: true
		return testNumber
	}
}

