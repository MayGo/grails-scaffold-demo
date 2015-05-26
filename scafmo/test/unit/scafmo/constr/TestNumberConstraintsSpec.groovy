package scafmo.constr

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(TestNumber)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TestNumberConstraintsSpec extends Specification {

	def setup() {
		//mock a TestNumber with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( TestNumber, [ new TestNumber() ] )
	}

	@Unroll("TestNumber constraint on field '#field' with value '#val' gets '#error'")
	def "All TestNumber constraints"() {
		when:
			TestNumber obj = new TestNumber()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'valid' | 'floatNrScale' | ConstraintHelper.getScale(1) 
			'valid' | 'floatNrScale' | ConstraintHelper.getScale(2) 
			'valid' | 'floatNrScale' | ConstraintHelper.getScale(3) 
			'inList' | 'integerNrInList' | '111112'
			'valid' | 'integerNrInList' | '1'
			'valid' | 'integerNrMax' | 3
			'max' | 'integerNrMax' | 3 + 1
			'valid' | 'integerNrMin' | 2
			'min' | 'integerNrMin' | 2 - 1
			'notEqual' | 'integerNrNotEqual' | '1'
			'valid' | 'integerNrNotEqual' | '11112'
			'valid' | 'integerNrRange' | 18
			'range' | 'integerNrRange' | 17
			'valid' | 'integerNrRange' | 65
			'range' | 'integerNrRange' | 66

	}
}
