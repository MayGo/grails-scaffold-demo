package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PersonCollectionlessModifyService {
	def grailsWebDataBinder

	PersonCollectionless createPersonCollectionless(Map data){
		PersonCollectionless personCollectionless = PersonCollectionless.newInstance()
		return createOrUpdate(personCollectionless, data)
	}

	PersonCollectionless updatePersonCollectionless(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		PersonCollectionless personCollectionless = queryForPersonCollectionless(data.id)

		if(!personCollectionless){
			throw new ResourceNotFound("No PersonCollectionless found with Id :[${data.id}]")
		}

		return createOrUpdate(personCollectionless, data)
	}

	PersonCollectionless createOrUpdate(PersonCollectionless personCollectionless, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind personCollectionless, data as SimpleMapDataBindingSource

		personCollectionless.save flush: true, failOnError: true

		return personCollectionless
	}

	PersonCollectionless queryForPersonCollectionless(long id){
		return PersonCollectionless.get(id)
	}
}
