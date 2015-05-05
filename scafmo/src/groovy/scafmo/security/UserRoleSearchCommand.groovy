package scafmo.security

import grails.validation.Validateable
import groovy.transform.ToString

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
