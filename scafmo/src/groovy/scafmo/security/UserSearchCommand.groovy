package scafmo.security

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class UserSearchCommand {

	Long id
	String searchString
	Boolean accountExpired
	Boolean accountLocked
	Boolean enabled
	Boolean passwordExpired
	String username

	static constraints = {
		id nullable: true
		searchString nullable: true
		accountExpired nullable: true
		accountLocked nullable: true
		enabled nullable: true
		passwordExpired nullable: true
		username nullable: true

	}
}
