package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class PetTypeModifyService {
	def grailsWebDataBinder

	PetType createPetType(Map data){
		PetType petType = PetType.newInstance()
		return createOrUpdate(petType, data)
	}

	PetType updatePetType(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		PetType petType = queryForPetType(data.id)

		if(!petType){
			throw new ResourceNotFound("No PetType found with Id :[${data.id}]")
		}

		return createOrUpdate(petType, data)
	}

	PetType createOrUpdate(PetType petType, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind petType, data as SimpleMapDataBindingSource

		petType.save flush: true, failOnError: true

		return petType
	}

	PetType queryForPetType(long id){
		return PetType.get(id)
	}
}
