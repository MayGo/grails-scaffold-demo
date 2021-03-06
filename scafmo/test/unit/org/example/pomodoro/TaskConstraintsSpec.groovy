package org.example.pomodoro

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper

@TestFor(Task)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TaskConstraintsSpec extends Specification {

	def setup() {
		//mock a Task with some data
		//(put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Task, [ new Task() ] )
	}

	@Unroll("Task constraint on field '#field' with value '#val' gets '#error'")
	def "All Task constraints"() {
		when:
			Task obj = new Task()
			obj."$field" = val

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'valid' | 'id' | 1 // Keep always one here or remove test
			'blank' | 'details' | ''
			'valid' | 'details' | ConstraintHelper.getLongString(1000)
			'maxSize' | 'details' | ConstraintHelper.getLongString(1001)
			'nullable' | 'details' | null
			'inList' | 'status' | 'Open111111'
			'valid' | 'status' | 'Open'
			'nullable' | 'status' | null
			'blank' | 'summary' | ''
			'nullable' | 'summary' | null
			'valid' | 'timeSpent' | 0
			'min' | 'timeSpent' | 0 - 1
			'nullable' | 'timeSpent' | null

	}
}
