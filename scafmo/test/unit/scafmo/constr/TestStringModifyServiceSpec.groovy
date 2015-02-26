package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TestStringModifyService)
@Mock(TestString)
class TestStringModifyServiceSpec extends Specification {

	void 'Creating TestString with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createTestString(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating TestString with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createTestString(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating TestString with valid data returns TestString instance'() {

		setup:
			Map data = validData()
		when:
			TestString testString = service.createTestString(data)
		then:
			testString != null
			testString.id != null
	}

	void 'Updating TestString without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateTestString(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestString with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updateTestString(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating TestString with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateTestString(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating TestString with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTestString().id
		when:
			TestString testString = service.updateTestString(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating TestString with valid data returns TestString instance'() {

		setup:
			Map data = validData()
			data.id = createValidTestString().id
		when:
			TestString testString = service.updateTestString(data)
		then:
			testString != null
			testString.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"blankStr":"Blank 756","creditCardStr":"372886934857774","emailStr":"test757@test.com","inListStr":"test1","matchesStr":"ABC","maxSizeStr":"ABCDE","minSizeStr":"ABC","notEqualStr":"notEqualStr 758","sizeStr":"sizeStr","uniqueStr":"U 759","urlStr":"http://www.test760.com"]

		return data
	}

	TestString createValidTestString(){
		TestString testString = new TestString(validData())
		testString.save flush:true, failOnError: true
		return testString
	}

}

