package scafmo.security

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Role)
class RoleConstraintsSpec extends Specification {

	def setup() {
		//mock a Role with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Role, [ new Role() ] )
	}

	@Unroll("Role constraint on field '#field' with value '#val' gets error '#error'")
	def "All Role constraints fails"() {
		when:
			def obj = new Role("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'authority' | ''
			'nullable' | 'authority' | null

	}
}
