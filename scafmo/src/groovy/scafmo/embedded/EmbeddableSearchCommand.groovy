package scafmo.embedded

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class EmbeddableSearchCommand {

	Long id
	String searchString
	List<Map> myAcs
	Map myAc
	String str

	static constraints = {
		id nullable: true
		searchString nullable: true
		myAcs nullable: true
		myAc nullable: true
		str nullable: true

	}
}
