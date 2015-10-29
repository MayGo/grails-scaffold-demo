package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(TestOtherSearchService)
@Mock(TestOther)
class TestOtherSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering TestOther without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestOther with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestOther with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering TestOther with valid id returns TestOther instance'() {

		setup:
			Long testOtherId = createValidTestOther().id
		when:
			TestOther testOther = service.queryForRead(testOtherId)
		then:
			testOther != null
			testOther.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'booleanNullable':  false,
  'testDate':  new Date().clearTime(),
  'testEnum':  'TEST_1'
]
		return data
	}

	TestOther createValidTestOther() {
		TestOther testOther = new TestOther(validData())
		testOther.save flush: true, failOnError: true
		return testOther
	}
}

