package scafmo.security


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import scafmo.security.Role
import scafmo.security.User

@Validateable
@ToString
class UserRoleSearchCommand {

	Long id
	String searchString
	Long role
	List<Long> roles
	Long user
	List<Long> users

	static constraints = {
		id nullable: true
		searchString nullable: true
		role nullable: true
		roles nullable: true
		user nullable: true
		users nullable: true

	}
}
