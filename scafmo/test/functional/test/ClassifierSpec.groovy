package test

import spock.lang.Shared
import spock.lang.Ignore
import org.springframework.http.HttpStatus
import defpackage.RestQueries
import spock.lang.Specification

class ClassifierSpec extends Specification implements RestQueries{

	
	String REST_URL = "${APP_URL}/classifiers"
	
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

	void 'Test creating another Classifier instance.'() {//This is for creating some data to test list sorting
		when: 'Create classifier'
			response = sendCreateWithData(){
