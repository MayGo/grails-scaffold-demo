package org.example.pomodoro

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Tag)
class TagConstraintsSpec extends Specification {

	def setup() {
		//mock a Tag with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Tag, [ new Tag() ] )
	}

	@Unroll("Tag constraint on field '#field' with value '#val' gets '#error'")
	def "All Tag constraints"() {
		when:
			def obj = new Tag("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'name' | ''
			'nullable' | 'name' | null

	}
}
