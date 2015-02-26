package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Vet)
class VetConstraintsSpec extends Specification {

	def setup() {
		//mock a Vet with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Vet, [ new Vet() ] )
	}

	@Unroll("Vet constraint on field '#field' with value '#val' gets error '#error'")
	def "All Vet constraints fails"() {
		when:
			def obj = new Vet("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'firstName' | ''
			'nullable' | 'firstName' | null
			'nullable' | 'lastName' | ''
			'nullable' | 'lastName' | null

	}
}
