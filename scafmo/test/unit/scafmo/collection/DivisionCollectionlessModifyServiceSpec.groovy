package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(DivisionCollectionlessModifyService)
@Mock(DivisionCollectionless)
class DivisionCollectionlessModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating DivisionCollectionless with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createDivisionCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating DivisionCollectionless with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createDivisionCollectionless(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating DivisionCollectionless with valid data returns DivisionCollectionless instance'() {
		setup:
			Map data = validData()
		when:
			DivisionCollectionless divisionCollectionless = service.createDivisionCollectionless(data)
		then:
			divisionCollectionless != null
			divisionCollectionless.id != null
	}

	void 'Updating DivisionCollectionless without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollectionless with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollectionless with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating DivisionCollectionless with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidDivisionCollectionless().id
		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating DivisionCollectionless with valid data returns DivisionCollectionless instance'() {

		setup:
			Map data = validData()
			data.id = createValidDivisionCollectionless().id
		when:
			DivisionCollectionless divisionCollectionless = service.updateDivisionCollectionless(data)
		then:
			divisionCollectionless != null
			divisionCollectionless.id == 1
	}

	void 'Deleting DivisionCollectionless without id is not possible'() {
		when:
			service.deleteDivisionCollectionless(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting DivisionCollectionless with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteDivisionCollectionless(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting DivisionCollectionless with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteDivisionCollectionless(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved DivisionCollectionless is possible'() {
		setup:
			Long divisionCollectionlessId = createValidDivisionCollectionless().id
			DivisionCollectionless divisionCollectionless = DivisionCollectionless.findById(divisionCollectionlessId).find()
		when:
			service.deleteDivisionCollectionless(divisionCollectionlessId)
		then:
			divisionCollectionless != null
			DivisionCollectionless.findById(divisionCollectionlessId) == null
	}

	Map invalidData() {

		return ['name': '']
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Division152'
]
		return data
	}

	DivisionCollectionless createValidDivisionCollectionless() {
		DivisionCollectionless divisionCollectionless = new DivisionCollectionless(validData())
		divisionCollectionless.save flush: true, failOnError: true
		return divisionCollectionless
	}

}

