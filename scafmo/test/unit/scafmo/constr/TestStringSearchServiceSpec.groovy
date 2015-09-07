package scafmo.constr

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(TestStringSearchService)
@Mock(TestString)
class TestStringSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering TestString without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestString with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering TestString with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering TestString with valid id returns TestString instance'() {

		setup:
			Long testStringId = createValidTestString().id
		when:
			TestString testString = service.queryForRead(testStringId)
		then:
			testString != null
			testString.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'blankStr':  'Blank 756',
  'creditCardStr':  '372886934857774',
  'emailStr':  'test757@test.com',
  'inListStr':  'test1',
  'matchesStr':  'ABC',
  'maxSizeStr':  'ABCDE',
  'minSizeStr':  'ABC',
  'notEqualStr':  'notEqualStr 758',
  'sizeStr':  'sizeStr',
  'textareaStr':  'textareaStr',
  'uniqueStr':  'U 759',
  'urlStr':  'http://www.test760.com'
]
		return data
	}

	TestString createValidTestString() {
		TestString testString = new TestString(validData())
		testString.save flush: true, failOnError: true
		return testString
	}
}

