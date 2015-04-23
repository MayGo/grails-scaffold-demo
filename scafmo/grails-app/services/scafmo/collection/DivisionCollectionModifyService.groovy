package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class DivisionCollectionModifyService {
	def grailsWebDataBinder

	DivisionCollection createDivisionCollection(Map data) {
		DivisionCollection divisionCollection = DivisionCollection.newInstance()
		return createOrUpdate(divisionCollection, data)
	}

	DivisionCollection updateDivisionCollection(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollection divisionCollection = DivisionCollection.where { id == data.id }.find()

		if (!divisionCollection) {
			throw new ResourceNotFound("No DivisionCollection found with Id :[${data.id}]")
		}

		return createOrUpdate(divisionCollection, data)
	}

	DivisionCollection createOrUpdate(DivisionCollection divisionCollection, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind divisionCollection, data as SimpleMapDataBindingSource

		divisionCollection.save failOnError: true

		return divisionCollection
	}

	void deleteDivisionCollection(Long divisionCollectionId) {
		if (!divisionCollectionId || divisionCollectionId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		DivisionCollection divisionCollection = DivisionCollection.where { id == divisionCollectionId }.find()

		if (!divisionCollection) {
			throw new ResourceNotFound("No DivisionCollection found with Id:$divisionCollectionId")
		}

		divisionCollection.delete()
	}
}

