package scafmo.security

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class RoleSearchCommand {

	Long id
	String searchString
	String authority

	static constraints = {
		id nullable: true
		searchString nullable: true
		authority nullable: true

	}
}
