package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class SpecialityModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Speciality createSpeciality(Map data) {
		Speciality speciality = Speciality.newInstance()
		return createOrUpdate(speciality, data)
	}

	Speciality updateSpeciality(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Speciality speciality = Speciality.where { id == objId }.find()

		if (!speciality) {
			throw new ResourceNotFound("No Speciality found with Id :[$objId]")
		}

		return createOrUpdate(speciality, data)
	}

	Speciality createOrUpdate(Speciality speciality, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind speciality, data as SimpleMapDataBindingSource

		speciality.save failOnError: true

		return speciality
	}

	void deleteSpeciality(Long specialityId) {
		if (!specialityId || specialityId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Speciality speciality = Speciality.where { id == specialityId }.find()

		if (!speciality) {
			throw new ResourceNotFound("No Speciality found with Id:$specialityId")
		}

		speciality.delete()
	}
}

