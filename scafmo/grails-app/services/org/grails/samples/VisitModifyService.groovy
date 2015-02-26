package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class VisitModifyService {
	def grailsWebDataBinder

	Visit createVisit(Map data){
		Visit visit = Visit.newInstance()
		return createOrUpdate(visit, data)
	}

	Visit updateVisit(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Visit visit = queryForVisit(data.id)

		if(!visit){
			throw new ResourceNotFound("No Visit found with Id :[${data.id}]")
		}

		return createOrUpdate(visit, data)
	}

	Visit createOrUpdate(Visit visit, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind visit, data as SimpleMapDataBindingSource

		visit.save flush: true, failOnError: true

		return visit
	}

	Visit queryForVisit(long id){
		return Visit.get(id)
	}
}
