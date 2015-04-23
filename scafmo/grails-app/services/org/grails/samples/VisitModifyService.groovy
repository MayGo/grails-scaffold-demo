package org.grails.samples

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class VisitModifyService {
	def grailsWebDataBinder

	Visit createVisit(Map data) {
		Visit visit = Visit.newInstance()
		return createOrUpdate(visit, data)
	}

	Visit updateVisit(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Visit visit = Visit.where { id == data.id }.find()

		if (!visit) {
			throw new ResourceNotFound("No Visit found with Id :[${data.id}]")
		}

		return createOrUpdate(visit, data)
	}

	Visit createOrUpdate(Visit visit, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind visit, data as SimpleMapDataBindingSource

		visit.save failOnError: true

		return visit
	}

	void deleteVisit(Long visitId) {
		if (!visitId || visitId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Visit visit = Visit.where { id == visitId }.find()

		if (!visit) {
			throw new ResourceNotFound("No Visit found with Id:$visitId")
		}

		visit.delete()
	}
}

