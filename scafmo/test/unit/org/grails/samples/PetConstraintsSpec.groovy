package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Pet)
class PetConstraintsSpec extends Specification {

	def setup() {
		//mock a Pet with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Pet, [ new Pet() ] )
	}

	@Unroll("Pet constraint on field '#field' with value '#val' gets '#error'")
	def "All Pet constraints"() {
		when:
			Pet obj = new Pet()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'birthDate' | null
			'blank' | 'name' | ''
			//'validator' | 'name' | 'CUSTOM_VALIDATOR'
			'nullable' | 'name' | null
			'nullable' | 'type' | null
			'nullable' | 'owner' | null

	}
}
