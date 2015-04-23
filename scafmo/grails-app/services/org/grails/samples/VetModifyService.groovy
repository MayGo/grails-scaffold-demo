package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class VetModifyService {
	def grailsWebDataBinder

	Vet createVet(Map data) {
		Vet vet = Vet.newInstance()
		return createOrUpdate(vet, data)
	}

	Vet updateVet(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Vet vet = Vet.where { id == data.id }.find()

		if (!vet) {
			throw new ResourceNotFound("No Vet found with Id :[${data.id}]")
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

