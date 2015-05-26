package scafmo.constr

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(TestOther)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TestOtherConstraintsSpec extends Specification {

	def setup() {
		//mock a TestOther with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( TestOther, [ new TestOther() ] )
	}

	@Unroll("TestOther constraint on field '#field' with value '#val' gets '#error'")
	def "All TestOther constraints"() {
		when:
			TestOther obj = new TestOther()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test

	}
}
