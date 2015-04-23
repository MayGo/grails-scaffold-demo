package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PersonModifyService {
	def grailsWebDataBinder

	Person createPerson(Map data) {
		Person person = Person.newInstance()
		return createOrUpdate(person, data)
	}

	Person updatePerson(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Person person = Person.where { id == data.id }.find()

		if (!person) {
			throw new ResourceNotFound("No Person found with Id :[${data.id}]")
		}

		return createOrUpdate(person, data)
	}

	Person createOrUpdate(Person person, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind person, data as SimpleMapDataBindingSource

		person.save failOnError: true

		return person
	}

	void deletePerson(Long personId) {
		if (!personId || personId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Person person = Person.where { id == personId }.find()

		if (!person) {
			throw new ResourceNotFound("No Person found with Id:$personId")
		}

		person.delete()
	}
}

