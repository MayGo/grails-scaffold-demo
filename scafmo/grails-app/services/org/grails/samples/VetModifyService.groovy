package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class VetModifyService {
	def grailsWebDataBinder

	Vet createVet(Map data){
		Vet vet = Vet.newInstance()
		return createOrUpdate(vet, data)
	}

	Vet updateVet(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Vet vet = queryForVet(data.id)

		if(!vet){
			throw new ResourceNotFound("No Vet found with Id :[${data.id}]")
		}

		return createOrUpdate(vet, data)
	}

	Vet createOrUpdate(Vet vet, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind vet, data as SimpleMapDataBindingSource

		vet.save flush: true, failOnError: true

		return vet
	}

	Vet queryForVet(long id){
		return Vet.get(id)
	}
}
