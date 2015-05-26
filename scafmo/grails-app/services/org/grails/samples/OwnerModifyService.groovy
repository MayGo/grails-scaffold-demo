package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class OwnerModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Owner createOwner(Map data) {
		Owner owner = Owner.newInstance()
		return createOrUpdate(owner, data)
	}

	Owner updateOwner(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Owner owner = Owner.where { id == objId }.find()

		if (!owner) {
			throw new ResourceNotFound("No Owner found with Id :[$objId]")
		}

		return createOrUpdate(owner, data)
	}

	Owner createOrUpdate(Owner owner, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind owner, data as SimpleMapDataBindingSource

		owner.save failOnError: true

		return owner
	}

	void deleteOwner(Long ownerId) {
		if (!ownerId || ownerId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Owner owner = Owner.where { id == ownerId }.find()

		if (!owner) {
			throw new ResourceNotFound("No Owner found with Id:$ownerId")
		}

		owner.delete()
	}
}

