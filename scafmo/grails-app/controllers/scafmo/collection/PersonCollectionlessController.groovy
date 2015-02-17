package scafmo.collection

import grails.transaction.Transactional
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import defpackage.CustomRestfulController

@Transactional(readOnly = true)
@RestApi(name = 'PersonCollectionless services', description = 'Methods for managing PersonCollectionlesss')
class PersonCollectionlessController extends CustomRestfulController<PersonCollectionless> {

	static responseFormats = ['json']

	PersonCollectionlessSearchService personCollectionlessSearchService

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
}
