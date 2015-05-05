package org.grails.samples

import grails.validation.Validateable
import groovy.transform.ToString

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
