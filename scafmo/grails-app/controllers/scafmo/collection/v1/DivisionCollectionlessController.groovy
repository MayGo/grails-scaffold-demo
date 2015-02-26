package scafmo.collection.v1

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
import scafmo.collection.DivisionCollectionless
import scafmo.collection.DivisionCollectionlessModifyService
import scafmo.collection.DivisionCollectionlessSearchService


@Transactional(readOnly = true)
@RestApi(name = 'DivisionCollectionless services', description = 'Methods for managing DivisionCollectionlesss')
class DivisionCollectionlessController extends CustomRestfulController<DivisionCollectionless> {

	static namespace = 'v1'

	static responseFormats = ['json']

	DivisionCollectionlessSearchService divisionCollectionlessSearchService
	DivisionCollectionlessModifyService divisionCollectionlessModifyService

	DivisionCollectionlessController() {
		super(DivisionCollectionless, false /* read-only */)
	}

	@RestApiMethod(description = 'List all DivisionCollectionlesss', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of DivisionCollectionless to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollectionless list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollectionless list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved DivisionCollectionless list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = divisionCollectionlessSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a DivisionCollectionless')
	@RestApiParams(params=[
		@RestApiParam(name='id', type='long', paramType = RestApiParamType.PATH, description = 'The DivisionCollectionless id')
	])
	def show() {
		// We pass which fields to be rendered with the includes attributes,
		// we exclude the class property for all responses.
		respond queryForResource(params.id), [includes: includes, excludes: excludes]
	}

	/**
	 * Saves a DivisionCollectionless
	 */
	@Transactional
	@RestApiMethod(description = 'Save a DivisionCollectionless')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The Object id')
	])
	def save() {

		DivisionCollectionless instance = divisionCollectionlessModifyService.createDivisionCollectionless(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	/**
	 * Updates a DivisionCollectionless for the given id
	 * @param id
	 */
	@Transactional
	@RestApiMethod(description = 'Update a DivisionCollectionless')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The DivisionCollectionless id')
	])
	def update() {
		request.JSON.id = params.long('id')
		DivisionCollectionless instance = divisionCollectionlessModifyService.updateDivisionCollectionless(request.JSON)

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
	protected DivisionCollectionless queryForResource(Serializable id) {
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
