package org.grails.samples.v1

import grails.transaction.Transactional
import grails.validation.ValidationException
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.CustomRestfulController
import defpackage.exceptions.ResourceNotFound
import org.grails.samples.PetType
import org.grails.samples.PetTypeModifyService
import org.grails.samples.PetTypeSearchService


@Transactional(readOnly = true)
@RestApi(name = 'PetType services', description = 'Methods for managing PetTypes')
class PetTypeController extends CustomRestfulController<PetType> {

	static namespace = 'v1'

	static responseFormats = ['json']

	PetTypeSearchService petTypeSearchService
	PetTypeModifyService petTypeModifyService

	PetTypeController() {
		super(PetType, false /* read-only */)
	}

	@RestApiMethod(description = 'List all PetTypes', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of PetType to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PetType list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PetType list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PetType list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = petTypeSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a PetType')
	@RestApiParams(params=[
		@RestApiParam(name='id', type='long', paramType = RestApiParamType.PATH, description = 'The PetType id')
	])
	def show() {
		// We pass which fields to be rendered with the includes attributes,
		// we exclude the class property for all responses.
		respond queryForResource(params.id), [includes: includes, excludes: excludes]
	}

	/**
	 * Saves a PetType
	 */
	@Transactional
	@RestApiMethod(description = 'Save a PetType')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The Object id')
	])
	def save() {

		PetType instance = petTypeModifyService.createPetType(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	/**
	 * Updates a PetType for the given id
	 * @param id
	 */
	@Transactional
	@RestApiMethod(description = 'Update a PetType')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The PetType id')
	])
	def update() {
		request.JSON.id = params.long('id')
		PetType instance = petTypeModifyService.updatePetType(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.OK]
	}


	private getIncludes() {
		params.includes?.tokenize(',')
	}

	private getExcludes() {
		params.excludes?.tokenize(',')
	}

	@Override
	protected PetType queryForResource(Serializable id) {
		if (id.isNumber()) {
			resource.get(id)
		} else {
			notFound()
		}
	}

	def handleValidationException(ValidationException ex){
		respond ex.errors, [status: HttpStatus.UNPROCESSABLE_ENTITY]
	}
	def handleResourceNotFoundException(ResourceNotFound ex){
		log.error ex
		render status: HttpStatus.NOT_FOUND
	}

	def handleIllegalArgumentExceptionn(IllegalArgumentException ex){
		log.error ex
		respond ex, [status: HttpStatus.UNPROCESSABLE_ENTITY]
	}

}