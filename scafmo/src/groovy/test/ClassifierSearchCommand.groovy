package test


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString


@Validateable
@ToString
class ClassifierSearchCommand {

	Long id
	String searchString
	String classname
	String constant
	String description
	String propertyname

	static constraints = {
		id nullable: true
		searchString nullable: true
		classname nullable: true
		constant nullable: true
		description nullable: true
		propertyname nullable: true

	}
}
