package scafmo.embedded

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class EmbeddableModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Embeddable createEmbeddable(Map data) {
		Embeddable embeddable = Embeddable.newInstance()
		return createOrUpdate(embeddable, data)
	}

	Embeddable updateEmbeddable(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Embeddable embeddable = Embeddable.where { id == objId }.find()

		if (!embeddable) {
			throw new ResourceNotFound("No Embeddable found with Id :[$objId]")
		}

		return createOrUpdate(embeddable, data)
	}

	Embeddable createOrUpdate(Embeddable embeddable, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind embeddable, data as SimpleMapDataBindingSource

		embeddable.save failOnError: true

		return embeddable
	}

	void deleteEmbeddable(Long embeddableId) {
		if (!embeddableId || embeddableId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Embeddable embeddable = Embeddable.where { id == embeddableId }.find()

		if (!embeddable) {
			throw new ResourceNotFound("No Embeddable found with Id:$embeddableId")
		}

		embeddable.delete()
	}
}

