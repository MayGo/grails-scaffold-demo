package org.grails.samples


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import org.grails.samples.Visit
import org.grails.samples.PetType
import org.grails.samples.Owner

@Validateable
@ToString
class PetSearchCommand {

	Long id
	String searchString
	Date birthDate
	String name
	Long type
	List<Long> types
	Long owner
	List<Long> owners

	static constraints = {
		id nullable: true
		searchString nullable: true
		birthDate nullable: true
		name nullable: true
		type nullable: true
		types nullable: true
		owner nullable: true
		owners nullable: true

	}
}
