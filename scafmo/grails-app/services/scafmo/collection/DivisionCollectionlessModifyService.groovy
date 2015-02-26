package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class DivisionCollectionlessModifyService {
	def grailsWebDataBinder

	DivisionCollectionless createDivisionCollectionless(Map data){
		DivisionCollectionless divisionCollectionless = DivisionCollectionless.newInstance()
		return createOrUpdate(divisionCollectionless, data)
	}

	DivisionCollectionless updateDivisionCollectionless(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		DivisionCollectionless divisionCollectionless = queryForDivisionCollectionless(data.id)

		if(!divisionCollectionless){
			throw new ResourceNotFound("No DivisionCollectionless found with Id :[${data.id}]")
		}

		return createOrUpdate(divisionCollectionless, data)
	}

	DivisionCollectionless createOrUpdate(DivisionCollectionless divisionCollectionless, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind divisionCollectionless, data as SimpleMapDataBindingSource

		divisionCollectionless.save flush: true, failOnError: true

		return divisionCollectionless
	}

	DivisionCollectionless queryForDivisionCollectionless(long id){
		return DivisionCollectionless.get(id)
	}
}
