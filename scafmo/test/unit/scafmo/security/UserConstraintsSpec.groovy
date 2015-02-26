package scafmo.security

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(User)
class UserConstraintsSpec extends Specification {

	def setup() {
		//mock a User with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( User, [ new User() ] )
	}

	@Unroll("User constraint on field '#field' with value '#val' gets error '#error'")
	def "All User constraints fails"() {
		when:
			def obj = new User("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'username' | ''
			'nullable' | 'username' | null

	}
}
