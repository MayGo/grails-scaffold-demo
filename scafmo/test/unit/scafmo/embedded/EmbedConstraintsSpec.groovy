package scafmo.embedded

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Embed)
@SuppressWarnings(['DuplicateNumberLiteral'])
class EmbedConstraintsSpec extends Specification {

	def setup() {
		//mock a Embed with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Embed, [ new Embed() ] )
	}

	@Unroll("Embed constraint on field '#field' with value '#val' gets '#error'")
	def "All Embed constraints"() {
		when:
			Embed obj = new Embed()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test

	}
}
