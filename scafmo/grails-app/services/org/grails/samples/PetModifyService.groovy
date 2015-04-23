package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PetModifyService {
	def grailsWebDataBinder

	Pet createPet(Map data) {
		Pet pet = Pet.newInstance()
		return createOrUpdate(pet, data)
	}

	Pet updatePet(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Pet pet = Pet.where { id == data.id }.find()

		if (!pet) {
			throw new ResourceNotFound("No Pet found with Id :[${data.id}]")
		}

		return createOrUpdate(pet, data)
	}

	Pet createOrUpdate(Pet pet, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind pet, data as SimpleMapDataBindingSource

		pet.save failOnError: true

		return pet
	}

	void deletePet(Long petId) {
		if (!petId || petId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Pet pet = Pet.where { id == petId }.find()

		if (!pet) {
			throw new ResourceNotFound("No Pet found with Id:$petId")
		}

		pet.delete()
	}
}

