package scafmo.constr

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(TestNumber)
class TestNumberConstraintsSpec extends Specification {

	def setup() {
		//mock a TestNumber with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( TestNumber, [ new TestNumber() ] )
	}

	@Unroll("TestNumber constraint on field '#field' with value '#val' gets error '#error'")
	def "All TestNumber constraints fails"() {
		when:
			def obj = new TestNumber("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'scale' | 'floatNrScale' | null
			'inList' | 'integerNrInList' | null
			'max' | 'integerNrMax' | null
			'min' | 'integerNrMin' | null
			'notEqual' | 'integerNrNotEqual' | null
			'range' | 'integerNrRange' | null

	}
}
