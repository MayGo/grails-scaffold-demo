package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Visit)
class VisitConstraintsSpec extends Specification {

	def setup() {
		//mock a Visit with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Visit, [ new Visit() ] )
	}

	@Unroll("Visit constraint on field '#field' with value '#val' gets '#error'")
	def "All Visit constraints"() {
		when:
			def obj = new Visit("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'date' | null
			'nullable' | 'description' | ''
			'nullable' | 'description' | null
			'nullable' | 'pet' | null

	}
}