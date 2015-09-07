package scafmo.embedded

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Embeddable)
@SuppressWarnings(['DuplicateNumberLiteral'])
class EmbeddableConstraintsSpec extends Specification {

	def setup() {
		//mock a Embeddable with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Embeddable, [ new Embeddable() ] )
	}

	@Unroll("Embeddable constraint on field '#field' with value '#val' gets '#error'")
	def "All Embeddable constraints"() {
		when:
			Embeddable obj = new Embeddable()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test

	}
}
