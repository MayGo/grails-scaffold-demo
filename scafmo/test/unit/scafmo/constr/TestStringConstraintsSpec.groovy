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

	@Unroll("TestString constraint on field '#field' with value '#val' gets '#error'")
	def "All TestString constraints"() {
		when:
			def obj = new TestString("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'blankStr' | ''
			'nullable' | 'blankStr' | null
			'creditCard' | 'creditCardStr' | ConstraintHelper.getCreditCard(false)
			'email' | 'emailStr' | ConstraintHelper.getEmail(false)
			'inList' | 'inListStr' | 'test1111111'
			'valid' | 'inListStr' | 'test1'
			'matches' | 'matchesStr' | 'DOES_NOT_MATCH'
			'valid' | 'maxSizeStr' | ConstraintHelper.getLongString(5)
			'maxSize' | 'maxSizeStr' | ConstraintHelper.getLongString(6)
			'valid' | 'minSizeStr' | ConstraintHelper.getLongString(2)
			'minSize' | 'minSizeStr' | ConstraintHelper.getLongString(1)
			'notEqual' | 'notEqualStr' | 'test'
			'valid' | 'notEqualStr' | 'test11111'
			'valid' | 'sizeStr' | ConstraintHelper.getLongString(1)
			'valid' | 'sizeStr' | ConstraintHelper.getLongString(100)
			'size' | 'sizeStr' | ConstraintHelper.getLongString(101)
			'url' | 'urlStr' | ConstraintHelper.getUrl(false)

	}
}
