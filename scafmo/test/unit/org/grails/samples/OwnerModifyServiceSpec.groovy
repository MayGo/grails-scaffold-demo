package org.grails.samples

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(OwnerModifyService)
@Mock(Owner)
class OwnerModifyServiceSpec extends Specification {

	void 'Creating Owner with no data is not possible'() {

		setup:
			Map data = [:]
		when:
			service.createOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Owner with invalid data is not possible'() {

		setup:

			Map data = invalidData()
		when:
			service.createOwner(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Owner with valid data returns Owner instance'() {

		setup:
			Map data = validData()
		when:
			Owner owner = service.createOwner(data)
		then:
			owner != null
			owner.id != null
	}

	void 'Updating Owner without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Owner with illegal id is not possible'() {

		setup:
			Map data = [id:-1L]
		when:
			service.updateOwner(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Owner with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateOwner(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Owner with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidOwner().id
		when:
			Owner owner = service.updateOwner(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Owner with valid data returns Owner instance'() {

		setup:
			Map data = validData()
			data.id = createValidOwner().id
		when:
			Owner owner = service.updateOwner(data)
		then:
			owner != null
			owner.id == 1
	}

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"address":"address","city":"city","firstName":"firstName","lastName":"lastName","telephone":"555452"]

		return data
	}

	Owner createValidOwner(){
		Owner owner = new Owner(validData())
		owner.save flush:true, failOnError: true
		return owner
	}

}

