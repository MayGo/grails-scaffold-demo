package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(DivisionCollectionless)
class DivisionCollectionlessConstraintsSpec extends Specification {

	def setup() {
		//mock a DivisionCollectionless with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( DivisionCollectionless, [ new DivisionCollectionless() ] )
	}

	@Unroll("DivisionCollectionless constraint on field '#field' with value '#val' gets '#error'")
	def "All DivisionCollectionless constraints"() {
		when:
			DivisionCollectionless obj = new DivisionCollectionless()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'name' | null
			'blank' | 'name' | ''

	}
}
