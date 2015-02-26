package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Pet)
class PetConstraintsSpec extends Specification {

	def setup() {
		//mock a Pet with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Pet, [ new Pet() ] )
	}

	@Unroll("Pet constraint on field '#field' with value '#val' gets error '#error'")
	def "All Pet constraints fails"() {
		when:
			def obj = new Pet("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'birthDate' | null
			'nullable' | 'name' | ''
			'validator' | 'name' | null
			'nullable' | 'name' | null
			'nullable' | 'type' | null
			'nullable' | 'owner' | null

	}
}
