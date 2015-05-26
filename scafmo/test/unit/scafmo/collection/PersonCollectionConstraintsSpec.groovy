package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(PersonCollection)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PersonCollectionConstraintsSpec extends Specification {

	def setup() {
		//mock a PersonCollection with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( PersonCollection, [ new PersonCollection() ] )
	}

	@Unroll("PersonCollection constraint on field '#field' with value '#val' gets '#error'")
	def "All PersonCollection constraints"() {
		when:
			PersonCollection obj = new PersonCollection()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'age' | null
			'nullable' | 'name' | null
			'blank' | 'name' | ''

	}
}
