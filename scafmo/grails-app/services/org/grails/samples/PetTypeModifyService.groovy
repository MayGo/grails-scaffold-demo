package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class PetTypeModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	PetType createPetType(Map data) {
		PetType petType = PetType.newInstance()
		return createOrUpdate(petType, data)
	}

	PetType updatePetType(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		PetType petType = PetType.where { id == objId }.find()

		if (!petType) {
			throw new ResourceNotFound("No PetType found with Id :[$objId]")
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

