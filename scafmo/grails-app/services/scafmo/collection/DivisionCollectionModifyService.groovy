package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class DivisionCollectionModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	DivisionCollection createDivisionCollection(Map data) {
		DivisionCollection divisionCollection = DivisionCollection.newInstance()
		return createOrUpdate(divisionCollection, data)
	}

	DivisionCollection updateDivisionCollection(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		DivisionCollection divisionCollection = DivisionCollection.where { id == objId }.find()

		if (!divisionCollection) {
			throw new ResourceNotFound("No DivisionCollection found with Id :[$objId]")
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

