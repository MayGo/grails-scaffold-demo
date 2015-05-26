package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class VetModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Vet createVet(Map data) {
		Vet vet = Vet.newInstance()
		return createOrUpdate(vet, data)
	}

	Vet updateVet(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Vet vet = Vet.where { id == objId }.find()

		if (!vet) {
			throw new ResourceNotFound("No Vet found with Id :[$objId]")
		}

		return createOrUpdate(vet, data)
	}

	Vet createOrUpdate(Vet vet, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind vet, data as SimpleMapDataBindingSource

		vet.save failOnError: true

		return vet
	}

	void deleteVet(Long vetId) {
		if (!vetId || vetId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Vet vet = Vet.where { id == vetId }.find()

		if (!vet) {
			throw new ResourceNotFound("No Vet found with Id:$vetId")
		}

		vet.delete()
	}
}

