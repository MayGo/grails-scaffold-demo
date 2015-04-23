package scafmo.collection.v1

import grails.validation.ValidationException
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.exceptions.ResourceNotFound
import scafmo.collection.DivisionCollection
import scafmo.collection.DivisionCollectionModifyService
import scafmo.collection.DivisionCollectionSearchService

@RestApi(name = 'DivisionCollection services', description = 'Methods for managing DivisionCollections')
class DivisionCollectionController {

	static namespace = 'v1'

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	static responseFormats = ['json']

	DivisionCollectionSearchService divisionCollectionSearchService
	DivisionCollectionModifyService divisionCollectionModifyService

	@RestApiMethod(description = 'List all DivisionCollections', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of DivisionCollection to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollection list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollection list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollection list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = divisionCollectionSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a DivisionCollection')
	@RestApiParams(params=[
		@RestApiParam(
				name='id', type='long',
				paramType = RestApiParamType.PATH,
				description = 'The DivisionCollection id'
		)
	])
	def show() {
		respond divisionCollectionSearchService.queryForDivisionCollection(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Save a DivisionCollection')
	def save() {

		DivisionCollection instance = divisionCollectionModifyService.createDivisionCollection(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	@RestApiMethod(description = 'Edit a DivisionCollection')
	@RestApiParams(params = [
		@RestApiParam(
				name = 'id',
				type = 'long',
				paramType = RestApiParamType.PATH,
				description = 'The DivisionCollection id'
		)
	])
	def edit() {
		respond divisionCollectionSearchService.queryForDivisionCollection(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Update a DivisionCollection')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The DivisionCollection id'
			)
	])
	def update() {
		request.JSON.id = params.long('id')
		DivisionCollection instance = divisionCollectionModifyService.updateDivisionCollection(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}

	@RestApiMethod(description = 'Delete a DivisionCollection')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The DivisionCollection id'
			)
	])
	def delete() {
		divisionCollectionModifyService.deleteDivisionCollection(params.long('id'))
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
