package org.example.pomodoro

import grails.test.mixin.TestFor
import spock.lang.Unroll
import spock.lang.Specification
import defpackage.ConstraintHelper


@TestFor(Task)
class TaskConstraintsSpec extends Specification {

	def setup() {
		//mock a Task with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests( Task, [ new Task() ] )
	}

	@Unroll("Task constraint on field '#field' with value '#val' gets error '#error'")
	def "All Task constraints fails"() {
		when:
			def obj = new Task("$field": val)

		then:
			ConstraintHelper.validateConstraints(obj, field, error)

		where:
			error                  | field        | val
			'nullable' | 'details' | ''
			'maxSize' | 'details' | null
			'nullable' | 'details' | null
			'inList' | 'status' | null
			'nullable' | 'status' | null
			'nullable' | 'summary' | ''
			'nullable' | 'summary' | null
			'min' | 'timeSpent' | null
			'nullable' | 'timeSpent' | null

	}
}
