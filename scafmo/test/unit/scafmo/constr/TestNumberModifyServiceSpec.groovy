package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TestNumberModifyService)
@Mock(TestNumber)
class TestNumberModifyServiceSpec extends Specification {

	void 'Creating TestNumber with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createTestNumber(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating TestNumber with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createTestNumber(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating TestNumber with valid data returns TestNumber instance'() {

		setup:
			Map data = validData()
		when:
			TestNumber testNumber = service.createTestNumber(data)
		then:
			testNumber != null
			testNumber.id != null
	}

	void 'Updating TestNumber without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateTestNumber(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestNumber with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updateTestNumber(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestNumber with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateTestNumber(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating TestNumber with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTestNumber().id
		when:
			TestNumber testNumber = service.updateTestNumber(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating TestNumber with valid data returns TestNumber instance'() {

		setup:
			Map data = validData()
			data.id = createValidTestNumber().id
		when:
			TestNumber testNumber = service.updateTestNumber(data)
		then:
			testNumber != null
			testNumber.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"doubleNr":123.123,"floatNr":123.123,"floatNrScale":2.34,"integerNr":303,"integerNrInList":3,"integerNrMax":2,"integerNrMin":3,"integerNrNotEqual":2,"integerNrRange":19,"integerNrUnique":304,"longNr":4]

		return data
	}

	TestNumber createValidTestNumber(){
		TestNumber testNumber = new TestNumber(validData())
		testNumber.save flush:true, failOnError: true
		return testNumber
	}

}

