package scafmo.collection

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class PersonCollectionSearchCommand {

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
