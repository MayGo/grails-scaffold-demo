package org.example.pomodoro.v1

import grails.validation.ValidationException
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.exceptions.ResourceNotFound
import org.example.pomodoro.Tag
import org.example.pomodoro.TagModifyService
import org.example.pomodoro.TagSearchService

@RestApi(name = 'Tag services', description = 'Methods for managing Tags')
class TagController {

	static namespace = 'v1'

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	static responseFormats = ['json']

	TagSearchService tagSearchService
	TagModifyService tagModifyService

	@RestApiMethod(description = 'List all Tags', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of Tag to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Tag list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Tag list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved Tag list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = tagSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a Tag')
	@RestApiParams(params=[
		@RestApiParam(
				name='id', type='long',
				paramType = RestApiParamType.PATH,
				description = 'The Tag id'
		)
	])
	def show() {
		respond tagSearchService.queryForTag(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Save a Tag')
	def save() {

		Tag instance = tagModifyService.createTag(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	@RestApiMethod(description = 'Edit a Tag')
	@RestApiParams(params = [
		@RestApiParam(
				name = 'id',
				type = 'long',
				paramType = RestApiParamType.PATH,
				description = 'The Tag id'
		)
	])
	def edit() {
		respond tagSearchService.queryForTag(params.long('id')),
				[includes: includes, excludes: excludes]
	}

	@RestApiMethod(description = 'Update a Tag')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Tag id'
			)
	])
	def update() {
		request.JSON.id = params.long('id')
		Tag instance = tagModifyService.updateTag(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}

	@RestApiMethod(description = 'Delete a Tag')
	@RestApiParams(params = [
			@RestApiParam(
					name = 'id',
					type = 'long',
					paramType = RestApiParamType.PATH,
					description = 'The Tag id'
			)
	])
	def delete() {
		tagModifyService.deleteTag(params.long('id'))
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
