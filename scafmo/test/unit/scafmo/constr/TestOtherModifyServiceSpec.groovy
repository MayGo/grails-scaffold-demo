package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TestOtherModifyService)
@Mock(TestOther)
class TestOtherModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateTestOther(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestOther with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateTestOther(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating TestOther with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTestOther().id
		when:
			TestOther testOther = service.updateTestOther(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"booleanNullable":false,"testDate":"2015-02-25T22:00:00Z","testEnum":"TEST_1"]

		return data
	}

	TestOther createValidTestOther(){
		TestOther testOther = new TestOther(validData())
		testOther.save flush:true, failOnError: true
		return testOther
	}

}

