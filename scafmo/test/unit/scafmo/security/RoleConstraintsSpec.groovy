package scafmo.security

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Role)
@SuppressWarnings(['DuplicateNumberLiteral'])
class RoleConstraintsSpec extends Specification {

	def setup() {
		//mock a Role with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Role, [ new Role() ] )
	}

	@Unroll("Role constraint on field '#field' with value '#val' gets '#error'")
	def "All Role constraints"() {
		when:
			Role obj = new Role()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'blank' | 'authority' | ''
			'nullable' | 'authority' | null

	}
}
