package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PersonCollectionModifyService {
	def grailsWebDataBinder

	PersonCollection createPersonCollection(Map data){
		PersonCollection personCollection = PersonCollection.newInstance()
		return createOrUpdate(personCollection, data)
	}

	PersonCollection updatePersonCollection(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		PersonCollection personCollection = queryForPersonCollection(data.id)

		if(!personCollection){
			throw new ResourceNotFound("No PersonCollection found with Id :[${data.id}]")
		}

		return createOrUpdate(personCollection, data)
	}

	PersonCollection createOrUpdate(PersonCollection personCollection, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind personCollection, data as SimpleMapDataBindingSource

		personCollection.save flush: true, failOnError: true

		return personCollection
	}

	PersonCollection queryForPersonCollection(long id){
		return PersonCollection.get(id)
	}
}
