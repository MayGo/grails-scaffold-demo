package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class DivisionCollectionlessModifyService {
	def grailsWebDataBinder

	DivisionCollectionless createDivisionCollectionless(Map data) {
		DivisionCollectionless divisionCollectionless = DivisionCollectionless.newInstance()
		return createOrUpdate(divisionCollectionless, data)
	}

	DivisionCollectionless updateDivisionCollectionless(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollectionless divisionCollectionless = DivisionCollectionless.where { id == data.id }.find()

		if (!divisionCollectionless) {
			throw new ResourceNotFound("No DivisionCollectionless found with Id :[${data.id}]")
		}

		return createOrUpdate(divisionCollectionless, data)
	}

	DivisionCollectionless createOrUpdate(DivisionCollectionless divisionCollectionless, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind divisionCollectionless, data as SimpleMapDataBindingSource

		divisionCollectionless.save failOnError: true

		return divisionCollectionless
	}

	void deleteDivisionCollectionless(Long divisionCollectionlessId) {
		if (!divisionCollectionlessId || divisionCollectionlessId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollectionless divisionCollectionless = DivisionCollectionless.where { id == divisionCollectionlessId }.find()

		if (!divisionCollectionless) {
			throw new ResourceNotFound("No DivisionCollectionless found with Id:$divisionCollectionlessId")
		}

		divisionCollectionless.delete()
	}
}

