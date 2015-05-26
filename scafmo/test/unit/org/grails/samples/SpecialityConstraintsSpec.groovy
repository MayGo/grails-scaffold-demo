package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Speciality)
@SuppressWarnings(['DuplicateNumberLiteral'])
class SpecialityConstraintsSpec extends Specification {

	def setup() {
		//mock a Speciality with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Speciality, [ new Speciality() ] )
	}

	@Unroll("Speciality constraint on field '#field' with value '#val' gets '#error'")
	def "All Speciality constraints"() {
		when:
			Speciality obj = new Speciality()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'blank' | 'name' | ''
			'valid' | 'name' | ConstraintHelper.getLongString(3)
			'minSize' | 'name' | ConstraintHelper.getLongString(2)
			'valid' | 'name' | ConstraintHelper.getLongString(20)
			'maxSize' | 'name' | ConstraintHelper.getLongString(21)
			'nullable' | 'name' | null

	}
}
