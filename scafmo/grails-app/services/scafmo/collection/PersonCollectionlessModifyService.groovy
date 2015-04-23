package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PersonCollectionlessModifyService {
	def grailsWebDataBinder

	PersonCollectionless createPersonCollectionless(Map data) {
		PersonCollectionless personCollectionless = PersonCollectionless.newInstance()
		return createOrUpdate(personCollectionless, data)
	}

	PersonCollectionless updatePersonCollectionless(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollectionless personCollectionless = PersonCollectionless.where { id == data.id }.find()

		if (!personCollectionless) {
			throw new ResourceNotFound("No PersonCollectionless found with Id :[${data.id}]")
		}

		return createOrUpdate(personCollectionless, data)
	}

	PersonCollectionless createOrUpdate(PersonCollectionless personCollectionless, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind personCollectionless, data as SimpleMapDataBindingSource

		personCollectionless.save failOnError: true

		return personCollectionless
	}

	void deletePersonCollectionless(Long personCollectionlessId) {
		if (!personCollectionlessId || personCollectionlessId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollectionless personCollectionless = PersonCollectionless.where { id == personCollectionlessId }.find()

		if (!personCollectionless) {
			throw new ResourceNotFound("No PersonCollectionless found with Id:$personCollectionlessId")
		}

		personCollectionless.delete()
	}
}

