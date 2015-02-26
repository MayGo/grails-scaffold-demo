package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(PersonCollectionless)
class PersonCollectionlessConstraintsSpec extends Specification {

	def setup() {
		//mock a PersonCollectionless with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( PersonCollectionless, [ new PersonCollectionless() ] )
	}

	@Unroll("PersonCollectionless constraint on field '#field' with value '#val' gets '#error'")
	def "All PersonCollectionless constraints"() {
		when:
			def obj = new PersonCollectionless("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'age' | null
			'nullable' | 'name' | null
			'nullable' | 'name' | ''

	}
}
