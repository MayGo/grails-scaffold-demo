package scafmo.security

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(UserRole)
class UserRoleConstraintsSpec extends Specification {

	def setup() {
		//mock a UserRole with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( UserRole, [ new UserRole() ] )
	}

	@Unroll("UserRole constraint on field '#field' with value '#val' gets error '#error'")
	def "All UserRole constraints fails"() {
		when:
			def obj = new UserRole("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'role' | null
			'nullable' | 'user' | null

	}
}
