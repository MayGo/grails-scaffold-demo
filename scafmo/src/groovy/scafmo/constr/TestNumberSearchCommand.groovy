package scafmo.constr

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class TestNumberSearchCommand {

	Long id
	String searchString
	Double doubleNr
	Float floatNr
	Float floatNrScale
	Integer integerNr
	Integer integerNrInList
	Integer integerNrMax
	Integer integerNrMin
	Integer integerNrNotEqual
	Integer integerNrRange
	Integer integerNrUnique
	Long longNr

	static constraints = {
		id nullable: true
		searchString nullable: true
		doubleNr nullable: true
		floatNr nullable: true
		floatNrScale nullable: true
		integerNr nullable: true
		integerNrInList nullable: true
		integerNrMax nullable: true
		integerNrMin nullable: true
		integerNrNotEqual nullable: true
		integerNrRange nullable: true
		integerNrUnique nullable: true
		longNr nullable: true

	}
}
