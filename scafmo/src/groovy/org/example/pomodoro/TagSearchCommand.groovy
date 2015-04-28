package org.example.pomodoro


import grails.util.GrailsNameUtils
import grails.validation.Validateable
import groovy.transform.ToString

import org.example.pomodoro.Task

@Validateable
@ToString
class TagSearchCommand {

	Long id
	String searchString
	String name

	static constraints = {
		id nullable: true
		searchString nullable: true
		name nullable: true

	}
}
