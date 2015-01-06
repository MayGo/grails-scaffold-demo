package org.grails.samples


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.pojo.RestApiParamType
import org.restapidoc.pojo.RestApiVerb
import defpackage.CustomRestfulController

@Transactional(readOnly = true)
@RestApi(name = "Visit services", description = "Methods for managing Visits")
class VisitController extends CustomRestfulController<Visit>{

    static responseFormats = ['json']
    
    VisitService visitService
	
	VisitController() {
		super(Visit, false /* read-only */)
	}
	
	@RestApiMethod(description="List all Visits", listing = true)
	@RestApiParams(params=[
		@RestApiParam(name="max", type="long", paramType = RestApiParamType.QUERY, description = "Max number of Visit to retrieve"),
		@RestApiParam(name="offset", type="long", paramType = RestApiParamType.QUERY, description = "Retrieved Visit list offset"),
		@RestApiParam(name="order", type="string", paramType = RestApiParamType.QUERY, description = "Retrieved Visit list order",allowedvalues=['asc','desc']),
		@RestApiParam(name="sort", type="string", paramType = RestApiParamType.QUERY, description = "Retrieved Visit list sort")
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)
		// Parses params.query for dynamic search and uses params.offset/params.max for paging. Returns results for paging grid.
		// This is here so running demo works right away. Should be replaced with own service, eg: visitService.list(params)
		List results = visitService.parseParamsAndRetrieveListAndCount(params) 
        header 'Access-Control-Expose-Headers', 'total'
		header 'total', results.totalCount
		
		respond results, [includes: includes, excludes: excludes]
	}
	
	@RestApiMethod(description="Get a Visit")
	@RestApiParams(params=[
		@RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The Visit id")
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
	protected Visit queryForResource(Serializable id) {
		if(id.isNumber()){
			resource.get(id)
		}else{
			notFound()
		}
	}
}
