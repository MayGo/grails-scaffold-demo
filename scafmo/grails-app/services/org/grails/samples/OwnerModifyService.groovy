package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class OwnerModifyService {
	def grailsWebDataBinder

	Owner createOwner(Map data){
		Owner owner = Owner.newInstance()
		return createOrUpdate(owner, data)
	}

	Owner updateOwner(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Owner owner = queryForOwner(data.id)

		if(!owner){
			throw new ResourceNotFound("No Owner found with Id :[${data.id}]")
		}

		return createOrUpdate(owner, data)
	}

	Owner createOrUpdate(Owner owner, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind owner, data as SimpleMapDataBindingSource

		owner.save flush: true, failOnError: true

		return owner
	}

	Owner queryForOwner(long id){
		return Owner.get(id)
	}
}
