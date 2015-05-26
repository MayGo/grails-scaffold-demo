package scafmo.collection

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class PersonCollectionModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	PersonCollection createPersonCollection(Map data) {
		PersonCollection personCollection = PersonCollection.newInstance()
		return createOrUpdate(personCollection, data)
	}

	PersonCollection updatePersonCollection(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		PersonCollection personCollection = PersonCollection.where { id == objId }.find()

		if (!personCollection) {
			throw new ResourceNotFound("No PersonCollection found with Id :[$objId]")
		}

		return createOrUpdate(personCollection, data)
	}

	PersonCollection createOrUpdate(PersonCollection personCollection, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind personCollection, data as SimpleMapDataBindingSource

		personCollection.save failOnError: true

		return personCollection
	}

	void deletePersonCollection(Long personCollectionId) {
		if (!personCollectionId || personCollectionId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PersonCollection personCollection = PersonCollection.where { id == personCollectionId }.find()

		if (!personCollection) {
			throw new ResourceNotFound("No PersonCollection found with Id:$personCollectionId")
		}

		personCollection.delete()
	}
}

