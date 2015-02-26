package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PersonModifyService {
	def grailsWebDataBinder

	Person createPerson(Map data){
		Person person = Person.newInstance()
		return createOrUpdate(person, data)
	}

	Person updatePerson(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Person person = queryForPerson(data.id)

		if(!person){
			throw new ResourceNotFound("No Person found with Id :[${data.id}]")
		}

		return createOrUpdate(person, data)
	}

	Person createOrUpdate(Person person, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind person, data as SimpleMapDataBindingSource

		person.save flush: true, failOnError: true

		return person
	}

	Person queryForPerson(long id){
		return Person.get(id)
	}
}
