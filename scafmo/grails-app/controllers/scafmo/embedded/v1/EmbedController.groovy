package scafmo.embedded.v1

import grails.validation.ValidationException
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.exceptions.ResourceNotFound
import scafmo.embedded.Embed
import scafmo.embedded.EmbedModifyService
import scafmo.embedded.EmbedSearchService
import scafmo.embedded.EmbedSearchCommand

@RestApi(name = 'Embed services', description = 'Methods for managing Embeds')
class EmbedController {

	static namespace = 'v1'

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	static responseFormats = ['json']

	EmbedSearchService embedSearchService
	EmbedModifyService embedModifyService

	@RestApiMethod(description = 'List all Embeds', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of Embed to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Embed list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Embed list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Embed list sort')
	])
	def index(EmbedSearchCommand cmd) {
		params.max = Math.min(params.int('max') ?: 10, 100)

		if (cmd.hasErrors()) {
			throw new ValidationException('Search does not validate.', cmd.errors)
		}

		def result = embedSearchService.search(cmd, params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a Embed')
	@RestApiParams(params=[
		@RestApiParam(
				name='id', type='long',
				paramType = RestApiParamType.PATH,
				description = 'The Embed id'
		)
	])
	def show() {
		respond embedSearchService.queryForRead(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Save a Embed')
	def save() {

		Embed instance = embedModifyService.createEmbed(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	@RestApiMethod(description = 'Edit a Embed')
	@RestApiParams(params = [
		@RestApiParam(
				name = 'id',
				type = 'long',
				paramType = RestApiParamType.PATH,
				description = 'The Embed id'
		)
	])
	def edit() {
		respond embedSearchService.queryForRead(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Update a Embed')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Embed id'
			)
	])
	def update() {
		request.JSON.id = params.long('id')
		Embed instance = embedModifyService.updateEmbed(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}

	@RestApiMethod(description = 'Delete a Embed')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Embed id'
			)
	])
	def delete() {
		embedModifyService.deleteEmbed(params.long('id'))
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
