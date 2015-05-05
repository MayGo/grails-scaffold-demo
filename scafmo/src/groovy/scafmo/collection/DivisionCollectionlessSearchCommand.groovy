package scafmo.collection

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class DivisionCollectionlessSearchCommand {

	Long id
	String searchString
	String name
	Long headDivision
	List<Long> headDivisions

	static constraints = {
		id nullable: true
		searchString nullable: true
		name nullable: true
		headDivision nullable: true
		headDivisions nullable: true

	}
}
