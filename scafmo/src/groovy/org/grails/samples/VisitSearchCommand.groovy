package org.grails.samples


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import org.grails.samples.Pet

@Validateable
@ToString
class VisitSearchCommand {

	Long id
	String searchString
	Date date
	String description
	Long pet
	List<Long> pets

	static constraints = {
		id nullable: true
		searchString nullable: true
		date nullable: true
		description nullable: true
		pet nullable: true
		pets nullable: true

	}
}
