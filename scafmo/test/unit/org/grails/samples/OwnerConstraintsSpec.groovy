package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Owner)
class OwnerConstraintsSpec extends Specification {

	def setup() {
		//mock a Owner with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Owner, [ new Owner() ] )
	}

	@Unroll("Owner constraint on field '#field' with value '#val' gets '#error'")
	def "All Owner constraints"() {
		when:
			def obj = new Owner("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'address' | ''
			'nullable' | 'address' | null
			'nullable' | 'city' | ''
			'nullable' | 'city' | null
			'nullable' | 'firstName' | ''
			'nullable' | 'firstName' | null
			'nullable' | 'lastName' | ''
			'nullable' | 'lastName' | null
			'matches' | 'telephone' | 'DOES_NOT_MATCH'
			'nullable' | 'telephone' | ''
			'nullable' | 'telephone' | null

	}
}