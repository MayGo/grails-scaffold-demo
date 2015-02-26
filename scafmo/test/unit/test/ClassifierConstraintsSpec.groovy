package test

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Classifier)
class ClassifierConstraintsSpec extends Specification {

	def setup() {
		//mock a Classifier with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Classifier, [ new Classifier() ] )
	}

	@Unroll("Classifier constraint on field '#field' with value '#val' gets error '#error'")
	def "All Classifier constraints fails"() {
		when:
			def obj = new Classifier("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'classname' | null
			'size' | 'constant' | ConstraintHelper.getLongString(101)
			'nullable' | 'constant' | ''
			'nullable' | 'constant' | null
			'size' | 'description' | ConstraintHelper.getLongString(501)
			'nullable' | 'description' | ''
			'nullable' | 'description' | null
			'nullable' | 'propertyname' | null

	}
}
