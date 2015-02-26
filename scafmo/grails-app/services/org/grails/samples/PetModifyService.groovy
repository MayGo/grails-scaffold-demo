package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PetModifyService {
	def grailsWebDataBinder

	Pet createPet(Map data){
		Pet pet = Pet.newInstance()
		return createOrUpdate(pet, data)
	}

	Pet updatePet(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Pet pet = queryForPet(data.id)

		if(!pet){
			throw new ResourceNotFound("No Pet found with Id :[${data.id}]")
		}

		return createOrUpdate(pet, data)
	}

	Pet createOrUpdate(Pet pet, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind pet, data as SimpleMapDataBindingSource

		pet.save flush: true, failOnError: true

		return pet
	}

	Pet queryForPet(long id){
		return Pet.get(id)
	}
}
