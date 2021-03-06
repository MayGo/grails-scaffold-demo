package org.grails.samples

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class SpecialitySearchCommand {

	Long id
	String searchString
	String name

	static constraints = {
		id nullable: true
		searchString nullable: true
		name nullable: true

	}
}
