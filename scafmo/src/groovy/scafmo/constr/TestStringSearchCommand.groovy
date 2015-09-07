package scafmo.constr

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class TestStringSearchCommand {

	Long id
	String searchString
	String blankStr
	String creditCardStr
	String emailStr
	String inListStr
	String matchesStr
	String maxSizeStr
	String minSizeStr
	String notEqualStr
	String sizeStr
	String textareaStr
	String uniqueStr
	String urlStr

	static constraints = {
		id nullable: true
		searchString nullable: true
		blankStr nullable: true
		creditCardStr nullable: true
		emailStr nullable: true
		inListStr nullable: true
		matchesStr nullable: true
		maxSizeStr nullable: true
		minSizeStr nullable: true
		notEqualStr nullable: true
		sizeStr nullable: true
		textareaStr nullable: true
		uniqueStr nullable: true
		urlStr nullable: true

	}
}
