package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(VisitModifyService)
@Mock(Visit)
class VisitModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateVisit(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Visit with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateVisit(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Visit with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidVisit().id
		when:
			Visit visit = service.updateVisit(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"date":"2015-02-25T22:00:00Z","description":"description","pet":["id":302,"version":0]]

		return data
	}

	Visit createValidVisit(){
		Visit visit = new Visit(validData())
		visit.save flush:true, failOnError: true
		return visit
	}

}

