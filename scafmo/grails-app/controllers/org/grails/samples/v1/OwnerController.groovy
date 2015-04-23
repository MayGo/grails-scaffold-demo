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
import org.grails.samples.Owner
import org.grails.samples.OwnerModifyService
import org.grails.samples.OwnerSearchService

@RestApi(name = 'Owner services', description = 'Methods for managing Owners')
class OwnerController {

	static namespace = 'v1'

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	static responseFormats = ['json']

	OwnerSearchService ownerSearchService
	OwnerModifyService ownerModifyService

	@RestApiMethod(description = 'List all Owners', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of Owner to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Owner list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Owner list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Owner list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = ownerSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a Owner')
	@RestApiParams(params=[
		@RestApiParam(
				name='id', type='long',
				paramType = RestApiParamType.PATH,
				description = 'The Owner id'
		)
	])
	def show() {
		respond ownerSearchService.queryForOwner(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Save a Owner')
	def save() {

		Owner instance = ownerModifyService.createOwner(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	@RestApiMethod(description = 'Edit a Owner')
	@RestApiParams(params = [
		@RestApiParam(
				name = 'id',
				type = 'long',
				paramType = RestApiParamType.PATH,
				description = 'The Owner id'
		)
	])
	def edit() {
		respond ownerSearchService.queryForOwner(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Update a Owner')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Owner id'
			)
	])
	def update() {
		request.JSON.id = params.long('id')
		Owner instance = ownerModifyService.updateOwner(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}

	@RestApiMethod(description = 'Delete a Owner')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Owner id'
			)
	])
	def delete() {
		ownerModifyService.deleteOwner(params.long('id'))
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
