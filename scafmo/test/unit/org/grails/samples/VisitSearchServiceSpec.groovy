package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(VisitSearchService)
@Mock(Visit)
class VisitSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Visit without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Visit with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Visit with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Visit with valid id returns Visit instance'() {

		setup:
			Long visitId = createValidVisit().id
		when:
			Visit visit = service.queryForRead(visitId)
		then:
			visit != null
			visit.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
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

