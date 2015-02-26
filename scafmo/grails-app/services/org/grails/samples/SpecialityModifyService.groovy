package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class SpecialityModifyService {
	def grailsWebDataBinder

	Speciality createSpeciality(Map data){
		Speciality speciality = Speciality.newInstance()
		return createOrUpdate(speciality, data)
	}

	Speciality updateSpeciality(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Speciality speciality = queryForSpeciality(data.id)

		if(!speciality){
			throw new ResourceNotFound("No Speciality found with Id :[${data.id}]")
		}

		return createOrUpdate(speciality, data)
	}

	Speciality createOrUpdate(Speciality speciality, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind speciality, data as SimpleMapDataBindingSource

		speciality.save flush: true, failOnError: true

		return speciality
	}

	Speciality queryForSpeciality(long id){
		return Speciality.get(id)
	}
}
