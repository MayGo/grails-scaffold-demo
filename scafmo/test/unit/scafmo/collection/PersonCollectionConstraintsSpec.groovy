package scafmo.collection

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(PersonCollection)
class PersonCollectionConstraintsSpec extends Specification {

	def setup() {
		//mock a PersonCollection with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( PersonCollection, [ new PersonCollection() ] )
	}

	@Unroll("PersonCollection constraint on field '#field' with value '#val' gets error '#error'")
	def "All PersonCollection constraints fails"() {
		when:
			def obj = new PersonCollection("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'age' | null
			'nullable' | 'name' | null
			'nullable' | 'name' | ''

	}
}
