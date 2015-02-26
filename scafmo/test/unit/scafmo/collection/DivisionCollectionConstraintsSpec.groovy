package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(DivisionCollection)
class DivisionCollectionConstraintsSpec extends Specification {

	def setup() {
		//mock a DivisionCollection with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( DivisionCollection, [ new DivisionCollection() ] )
	}

	@Unroll("DivisionCollection constraint on field '#field' with value '#val' gets '#error'")
	def "All DivisionCollection constraints"() {
		when:
			def obj = new DivisionCollection("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'name' | null
			'nullable' | 'name' | ''

	}
}
