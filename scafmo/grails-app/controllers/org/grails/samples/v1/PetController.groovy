package org.grails.samples.v1

import grails.validation.ValidationException
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.exceptions.ResourceNotFound
import org.grails.samples.Pet
import org.grails.samples.PetModifyService
import org.grails.samples.PetSearchService

@RestApi(name = 'Pet services', description = 'Methods for managing Pets')
class PetController {

	static namespace = 'v1'

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	static responseFormats = ['json']

	PetSearchService petSearchService
	PetModifyService petModifyService

	@RestApiMethod(description = 'List all Pets', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of Pet to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Pet list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Pet list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Pet list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = petSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a Pet')
	@RestApiParams(params=[
		@RestApiParam(
				name='id', type='long',
				paramType = RestApiParamType.PATH,
				description = 'The Pet id'
		)
	])
	def show() {
		respond petSearchService.queryForPet(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Save a Pet')
	def save() {

		Pet instance = petModifyService.createPet(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	@RestApiMethod(description = 'Edit a Pet')
	@RestApiParams(params = [
		@RestApiParam(
				name = 'id',
				type = 'long',
				paramType = RestApiParamType.PATH,
				description = 'The Pet id'
		)
	])
	def edit() {
		respond petSearchService.queryForPet(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Update a Pet')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Pet id'
			)
	])
	def update() {
		request.JSON.id = params.long('id')
		Pet instance = petModifyService.updatePet(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}

	@RestApiMethod(description = 'Delete a Pet')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Pet id'
			)
	])
	def delete() {
		petModifyService.deletePet(params.long('id'))
		render status: HttpStatus.NO_CONTENT
	}

	private getIncludes() {
		params.includes?.tokenize(',')
	}

	private getExcludes() {
		params.excludes?.tokenize(',')
	}

	def handleValidationException(ValidationException ex) {
		respond ex.errors, [status: HttpStatus.UNPROCESSABLE_ENTITY]
	}

	def handleResourceNotFoundException(ResourceNotFound ex) {
		log.error ex
		render status: HttpStatus.NOT_FOUND
	}

	def handleIllegalArgumentExceptionn(IllegalArgumentException ex) {
		log.error ex
		Map errors = ['error': ex.message]
		respond errors as Object, [status: HttpStatus.UNPROCESSABLE_ENTITY]
	}
}
