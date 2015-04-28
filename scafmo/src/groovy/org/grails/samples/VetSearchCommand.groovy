package org.grails.samples


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import org.grails.samples.Speciality

@Validateable
@ToString
class VetSearchCommand {

	Long id
	String searchString
	String firstName
	String lastName

	static constraints = {
		id nullable: true
		searchString nullable: true
		firstName nullable: true
		lastName nullable: true

	}
}
