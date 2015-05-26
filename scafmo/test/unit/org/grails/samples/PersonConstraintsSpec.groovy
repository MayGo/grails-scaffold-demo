package org.grails.samples

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Person)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PersonConstraintsSpec extends Specification {

	def setup() {
		//mock a Person with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Person, [ new Person() ] )
	}

	@Unroll("Person constraint on field '#field' with value '#val' gets '#error'")
	def "All Person constraints"() {
		when:
			Person obj = new Person()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'blank' | 'firstName' | ''
			'nullable' | 'firstName' | null
			'blank' | 'lastName' | ''
			'nullable' | 'lastName' | null

	}
}
