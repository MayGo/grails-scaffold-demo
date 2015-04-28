package scafmo.constr


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import scafmo.constr.TestString
import scafmo.constr.TestOther.TestEnum

@Validateable
@ToString
class TestOtherSearchCommand {

	Long id
	String searchString
	Boolean booleanNullable
	Date testDate
	TestEnum testEnum
	Long testStringType
	List<Long> testStringTypes

	static constraints = {
		id nullable: true
		searchString nullable: true
		booleanNullable nullable: true
		testDate nullable: true
		testEnum nullable: true
		testStringType nullable: true
		testStringTypes nullable: true

	}
}
