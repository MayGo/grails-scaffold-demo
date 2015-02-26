package scafmo.constr.v1

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
import scafmo.constr.TestString
import scafmo.constr.TestStringModifyService
import scafmo.constr.TestStringSearchService


@Transactional(readOnly = true)
@RestApi(name = 'TestString services', description = 'Methods for managing TestStrings')
class TestStringController extends CustomRestfulController<TestString> {

	static namespace = 'v1'

	static responseFormats = ['json']

	TestStringSearchService testStringSearchService
	TestStringModifyService testStringModifyService

	TestStringController() {
		super(TestString, false /* read-only */)
	}

	@RestApiMethod(description = 'List all TestStrings', listing = true)
	@RestApiParams(params = [
		@RestApiParam(name = 'max', type='long', paramType = RestApiParamType.QUERY,
				description = 'Max number of TestString to retrieve'),
		@RestApiParam(name = 'offset', type='long', paramType = RestApiParamType.QUERY,
				description = 'Retrieved TestString list offset'),
		@RestApiParam(name = 'order', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved TestString list order', allowedvalues=['asc', 'desc']),
		@RestApiParam(name = 'sort', type='string', paramType = RestApiParamType.QUERY,
				description = 'Retrieved TestString list sort')
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def result = testStringSearchService.search(params)

		header 'Access-Control-Expose-Headers', 'total'
		header 'total', result.totalCount

		respond result, [includes: includes, excludes: excludes]
	}

	@RestApiMethod(description='Get a TestString')
	@RestApiParams(params=[
		@RestApiParam(name='id', type='long', paramType = RestApiParamType.PATH, description = 'The TestString id')
	])
	def show() {
		// We pass which fields to be rendered with the includes attributes,
		// we exclude the class property for all responses.
		respond queryForResource(params.id), [includes: includes, excludes: excludes]
	}

	/**
	 * Saves a TestString
	 */
	@Transactional
	@RestApiMethod(description = 'Save a TestString')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The Object id')
	])
	def save() {

		TestString instance = testStringModifyService.createTestString(request.JSON)

		response.addHeader(HttpHeaders.LOCATION,
				g.createLink(
						resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
						namespace: hasProperty('namespace') ? this.namespace : null))
		respond instance, [status: HttpStatus.CREATED]

	}

	/**
	 * Updates a TestString for the given id
	 * @param id
	 */
	@Transactional
	@RestApiMethod(description = 'Update a TestString')
	@RestApiParams(params = [
			@RestApiParam(name = 'id', type = 'long', paramType = RestApiParamType.PATH, description = 'The TestString id')
	])
	def update() {
		request.JSON.id = params.long('id')
		TestString instance = testStringModifyService.updateTestString(request.JSON)

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
	protected TestString queryForResource(Serializable id) {
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
