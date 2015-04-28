package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(VisitModifyService)
@Mock(Visit)
class VisitModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Visit with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createVisit(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Visit with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createVisit(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Visit with valid data returns Visit instance'() {
		setup:
			Map data = validData()
		when:
			Visit visit = service.createVisit(data)
		then:
			visit != null
			visit.id != null
	}

	void 'Updating Visit without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateVisit(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Visit with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateVisit(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Visit with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateVisit(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Visit with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidVisit().id
		when:
			service.updateVisit(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Visit with valid data returns Visit instance'() {

		setup:
			Map data = validData()
			data.id = createValidVisit().id
		when:
			Visit visit = service.updateVisit(data)
		then:
			visit != null
			visit.id == 1
	}

	void 'Deleting Visit without id is not possible'() {
		when:
			service.deleteVisit(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Visit with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteVisit(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Visit with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteVisit(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Visit is possible'() {
		setup:
			Long visitId = createValidVisit().id
			Visit visit = Visit.findById(visitId).find()
		when:
			service.deleteVisit(visitId)
		then:
			visit != null
			Visit.findById(visitId) == null
	}

	Map invalidData() {

		return ['date': null,
 'description': null,
 'pet': null]
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'date':  new Date().clearTime(),
  'description':  'description',
  'pet':  [
    'id':  302,
    'version':  0,
    'birthDate':  new Date().clearTime(),
    'name':  'Pet 305'
  ]
]
		return data
	}

	Visit createValidVisit() {
		Visit visit = new Visit(validData())
		visit.save flush: true, failOnError: true
		return visit
	}

}

