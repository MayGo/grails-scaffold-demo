package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(OwnerSearchService)
@Mock(Owner)
class OwnerSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Owner without id is not possible'() {

		when:
			service.queryForOwner(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Owner with illegal id is not possible'() {
		when:
			service.queryForOwner(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Owner with fictional id is not possible'() {
		when:
			service.queryForOwner(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Owner with valid id returns Owner instance'() {

		setup:
			Long ownerId = createValidOwner().id
		when:
			Owner owner = service.queryForOwner(ownerId)
		then:
			owner != null
			owner.id == 1
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'address':  'address',
  'city':  'city',
  'firstName':  'firstName',
  'lastName':  'lastName',
  'telephone':  '555452'
]
		return data
	}

	Owner createValidOwner() {
		Owner owner = new Owner(validData())
		owner.save flush: true, failOnError: true
		return owner
	}
}

