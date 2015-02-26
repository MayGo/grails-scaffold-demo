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
import scafmo.collection.PersonCollectionless
import scafmo.collection.PersonCollectionlessModifyService
import scafmo.collection.PersonCollectionlessSearchService


@Transactional(readOnly = true)
@RestApi(name = 'PersonCollectionless services', description = 'Methods for managing PersonCollectionlesss')
class PersonCollectionlessController extends CustomRestfulController<PersonCollectionless> {

	static namespace = 'v1'

	static responseFormats = ['json']

	PersonCollectionlessSearchService personCollectionlessSearchService
	PersonCollectionlessModifyService personCollectionlessModifyService

	PersonCollectionlessController() {
		super(PersonCollectionless, false /* read-only */)
	}

	@RestApiMethod(description = 'List all PersonCollectionlesss', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of PersonCollectionless to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PersonCollectionless list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PersonCollectionless list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved PersonCollectionless list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = personCollectionlessSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a PersonCollectionless')
	@RestApiParams(params=[
		@RestApiParam(name='id', type='long', paramType = RestApiParamType.PATH, description = 'The PersonCollectionless id')
	])
	def show() {
		// We pass which fields to be rendered with the includes attributes,
		// we exclude the class property for all responses.
		respond queryForResource(params.id), [includes: includes, excludes: excludes]
	}

	/**
	 * Saves a PersonCollectionless
	 */
	@Transactional
	@RestApiMethod(description = 'Save a PersonCollectionless')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The Object id')
	])
	def save() {

		PersonCollectionless instance = personCollectionlessModifyService.createPersonCollectionless(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	/**
	 * Updates a PersonCollectionless for the given id
	 * @param id
	 */
	@Transactional
	@RestApiMethod(description = 'Update a PersonCollectionless')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The PersonCollectionless id')
	])
	def update() {
		request.JSON.id = params.long('id')
		PersonCollectionless instance = personCollectionlessModifyService.updatePersonCollectionless(request.JSON)

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
	protected PersonCollectionless queryForResource(Serializable id) {
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
