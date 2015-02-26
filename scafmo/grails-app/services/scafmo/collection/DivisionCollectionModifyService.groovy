package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class DivisionCollectionModifyService {
	def grailsWebDataBinder

	DivisionCollection createDivisionCollection(Map data){
		DivisionCollection divisionCollection = DivisionCollection.newInstance()
		return createOrUpdate(divisionCollection, data)
	}

	DivisionCollection updateDivisionCollection(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		DivisionCollection divisionCollection = queryForDivisionCollection(data.id)

		if(!divisionCollection){
			throw new ResourceNotFound("No DivisionCollection found with Id :[${data.id}]")
		}

		return createOrUpdate(divisionCollection, data)
	}

	DivisionCollection createOrUpdate(DivisionCollection divisionCollection, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind divisionCollection, data as SimpleMapDataBindingSource

		divisionCollection.save flush: true, failOnError: true

		return divisionCollection
	}

	DivisionCollection queryForDivisionCollection(long id){
		return DivisionCollection.get(id)
	}
}
