package scafmo.collection


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
@RestApi(name = "DivisionCollectionless services", description = "Methods for managing DivisionCollectionlesss")
class DivisionCollectionlessController extends CustomRestfulController<DivisionCollectionless>{

    static responseFormats = ['json']
    
    DivisionCollectionlessService divisionCollectionlessService
	
	DivisionCollectionlessController() {
		super(DivisionCollectionless, false /* read-only */)
	}
	
	@RestApiMethod(description="List all DivisionCollectionlesss", listing = true)
	@RestApiParams(params=[
		@RestApiParam(name="max", type="long", paramType = RestApiParamType.QUERY, description = "Max number of DivisionCollectionless to retrieve"),
		@RestApiParam(name="offset", type="long", paramType = RestApiParamType.QUERY, description = "Retrieved DivisionCollectionless list offset"),
		@RestApiParam(name="order", type="string", paramType = RestApiParamType.QUERY, description = "Retrieved DivisionCollectionless list order",allowedvalues=['asc','desc']),
		@RestApiParam(name="sort", type="string", paramType = RestApiParamType.QUERY, description = "Retrieved DivisionCollectionless list sort")
	])
	def index(final Integer max) {
		params.max = Math.min(max ?: 10, 100)
		// Parses params.query for dynamic search and uses params.offset/params.max for paging. Returns results for paging grid.
		// This is here so running demo works right away. Should be replaced with own service, eg: divisionCollectionlessService.list(params)
		List results = divisionCollectionlessService.parseParamsAndRetrieveListAndCount(params) 
        header 'Access-Control-Expose-Headers', 'total'
		header 'total', results.totalCount
		
		respond results, [includes: includes, excludes: excludes]
	}
	
	@RestApiMethod(description="Get a DivisionCollectionless")
	@RestApiParams(params=[
		@RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The DivisionCollectionless id")
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
	protected DivisionCollectionless queryForResource(Serializable id) {
		if(id.isNumber()){
			resource.get(id)
		}else{
			notFound()
		}
	}
}
