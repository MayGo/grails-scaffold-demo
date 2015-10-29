package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TestOtherModifyService)
@Mock(TestOther)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TestOtherModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating TestOther with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createTestOther(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating TestOther with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createTestOther(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating TestOther with valid data returns TestOther instance'() {
		setup:
			Map data = validData()
		when:
			TestOther testOther = service.createTestOther(data)
		then:
			testOther != null
			testOther.id != null
	}

	void 'Updating TestOther without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateTestOther(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestOther with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateTestOther(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestOther with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateTestOther(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating TestOther with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTestOther().id
		when:
			service.updateTestOther(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating TestOther with valid data returns TestOther instance'() {

		setup:
			Map data = validData()
			data.id = createValidTestOther().id
		when:
			TestOther testOther = service.updateTestOther(data)
		then:
			testOther != null
			testOther.id == 1
	}

	void 'Deleting TestOther without id is not possible'() {
		when:
			service.deleteTestOther(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting TestOther with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteTestOther(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting TestOther with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteTestOther(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved TestOther is possible'() {
		setup:
			Long testOtherId = createValidTestOther().id
			TestOther testOther = TestOther.findById(testOtherId).find()
		when:
			service.deleteTestOther(testOtherId)
		then:
			testOther != null
			TestOther.findById(testOtherId) == null
	}

	Map invalidData() {

		return ['foo': 'Sadisfy empty data exception']
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

