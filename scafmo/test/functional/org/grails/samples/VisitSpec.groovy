package org.grails.samples

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class VisitSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/visits"
	
	@Shared
	Long domainId
	@Shared
	Long otherDomainId
	
	@Shared
	def authResponse
	
	@Shared
	def response
	
	def setupSpec() {
		authResponse = sendCorrectCredentials(APP_URL)
	}

	void 'Test creating another Visit instance.'() {//This is for creating some data to test list sorting
		when: 'Create visit'
			response = sendCreateWithData(){
