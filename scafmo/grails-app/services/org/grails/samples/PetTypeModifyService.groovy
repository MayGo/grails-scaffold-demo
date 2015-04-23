package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PetTypeModifyService {
	def grailsWebDataBinder

	PetType createPetType(Map data) {
		PetType petType = PetType.newInstance()
		return createOrUpdate(petType, data)
	}

	PetType updatePetType(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PetType petType = PetType.where { id == data.id }.find()

		if (!petType) {
			throw new ResourceNotFound("No PetType found with Id :[${data.id}]")
		}

		return createOrUpdate(petType, data)
	}

	PetType createOrUpdate(PetType petType, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind petType, data as SimpleMapDataBindingSource

		petType.save failOnError: true

		return petType
	}

	void deletePetType(Long petTypeId) {
		if (!petTypeId || petTypeId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		PetType petType = PetType.where { id == petTypeId }.find()

		if (!petType) {
			throw new ResourceNotFound("No PetType found with Id:$petTypeId")
		}

		petType.delete()
	}
}

