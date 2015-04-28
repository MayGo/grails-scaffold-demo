package scafmo.collection


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import scafmo.collection.DivisionCollectionless

@Validateable
@ToString
class PersonCollectionlessSearchCommand {

	Long id
	String searchString
	Integer age
	String name
	Long division
	List<Long> divisions

	static constraints = {
		id nullable: true
		searchString nullable: true
		age nullable: true
		name nullable: true
		division nullable: true
		divisions nullable: true

	}
}
