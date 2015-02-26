package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Speciality)
class SpecialityConstraintsSpec extends Specification {

	def setup() {
		//mock a Speciality with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Speciality, [ new Speciality() ] )
	}

	@Unroll("Speciality constraint on field '#field' with value '#val' gets error '#error'")
	def "All Speciality constraints fails"() {
		when:
			def obj = new Speciality("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'name' | ''
			'minSize' | 'name' | null
			'maxSize' | 'name' | null
			'nullable' | 'name' | null

	}
}
