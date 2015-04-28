package org.grails.samples


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import org.grails.samples.Pet

@Validateable
@ToString
class OwnerSearchCommand {

	Long id
	String searchString
	String address
	String city
	String firstName
	String lastName
	String telephone

	static constraints = {
		id nullable: true
		searchString nullable: true
		address nullable: true
		city nullable: true
		firstName nullable: true
		lastName nullable: true
		telephone nullable: true

	}
}
