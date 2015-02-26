package scafmo.constr

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(TestString)
class TestStringConstraintsSpec extends Specification {

	def setup() {
		//mock a TestString with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( TestString, [ new TestString() ] )
	}

	@Unroll("TestString constraint on field '#field' with value '#val' gets error '#error'")
	def "All TestString constraints fails"() {
		when:
			def obj = new TestString("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'blankStr' | ''
			'creditCard' | 'creditCardStr' | ConstraintHelper.getCreditCard(false)
			'email' | 'emailStr' | ConstraintHelper.getEmail(false)
			'inList' | 'inListStr' | null
			'matches' | 'matchesStr' | null
			'maxSize' | 'maxSizeStr' | null
			'minSize' | 'minSizeStr' | null
			'notEqual' | 'notEqualStr' | null
			'size' | 'sizeStr' | ConstraintHelper.getLongString(101)
			'url' | 'urlStr' | ConstraintHelper.getUrl(false)

	}
}
