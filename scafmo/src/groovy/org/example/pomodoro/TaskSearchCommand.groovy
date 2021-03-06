package org.example.pomodoro

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class TaskSearchCommand {

	Long id
	String searchString
	Date dateCreated
	Date deadline
	String details
	String status
	String summary
	Long timeSpent

	static constraints = {
		id nullable: true
		searchString nullable: true
		dateCreated nullable: true
		deadline nullable: true
		details nullable: true
		status nullable: true
		summary nullable: true
		timeSpent nullable: true

	}
}
