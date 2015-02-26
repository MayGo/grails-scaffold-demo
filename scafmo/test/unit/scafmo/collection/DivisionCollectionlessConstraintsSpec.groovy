package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(DivisionCollectionless)
class DivisionCollectionlessConstraintsSpec extends Specification {

	def setup() {
		//mock a DivisionCollectionless with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( DivisionCollectionless, [ new DivisionCollectionless() ] )
	}

	@Unroll("DivisionCollectionless constraint on field '#field' with value '#val' gets error '#error'")
	def "All DivisionCollectionless constraints fails"() {
		when:
			def obj = new DivisionCollectionless("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'name' | null
			'nullable' | 'name' | ''

	}
}
