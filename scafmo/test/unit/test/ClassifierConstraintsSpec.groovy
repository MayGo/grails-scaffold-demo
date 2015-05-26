package test

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Classifier)
@SuppressWarnings(['DuplicateNumberLiteral'])
class ClassifierConstraintsSpec extends Specification {

	def setup() {
		//mock a Classifier with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Classifier, [ new Classifier() ] )
	}

	@Unroll("Classifier constraint on field '#field' with value '#val' gets '#error'")
	def "All Classifier constraints"() {
		when:
			Classifier obj = new Classifier()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'nullable' | 'classname' | null
			'valid' | 'constant' | ConstraintHelper.getLongString(1)
			'valid' | 'constant' | ConstraintHelper.getLongString(100)
			'size' | 'constant' | ConstraintHelper.getLongString(101)
			'blank' | 'constant' | ''
			'nullable' | 'constant' | null
			'valid' | 'description' | ConstraintHelper.getLongString(1)
			'valid' | 'description' | ConstraintHelper.getLongString(500)
			'size' | 'description' | ConstraintHelper.getLongString(501)
			'blank' | 'description' | ''
			'nullable' | 'description' | null
			'nullable' | 'propertyname' | null

	}
}
