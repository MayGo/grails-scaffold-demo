package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(DivisionCollectionModifyService)
@Mock(DivisionCollection)
@SuppressWarnings(['DuplicateNumberLiteral'])
class DivisionCollectionModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating DivisionCollection with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createDivisionCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating DivisionCollection with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createDivisionCollection(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating DivisionCollection with valid data returns DivisionCollection instance'() {
		setup:
			Map data = validData()
		when:
			DivisionCollection divisionCollection = service.createDivisionCollection(data)
		then:
			divisionCollection != null
			divisionCollection.id != null
	}

	void 'Updating DivisionCollection without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateDivisionCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollection with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateDivisionCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollection with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateDivisionCollection(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating DivisionCollection with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidDivisionCollection().id
		when:
			service.updateDivisionCollection(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating DivisionCollection with valid data returns DivisionCollection instance'() {

		setup:
			Map data = validData()
			data.id = createValidDivisionCollection().id
		when:
			DivisionCollection divisionCollection = service.updateDivisionCollection(data)
		then:
			divisionCollection != null
			divisionCollection.id == 1
	}

	void 'Deleting DivisionCollection without id is not possible'() {
		when:
			service.deleteDivisionCollection(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting DivisionCollection with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteDivisionCollection(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting DivisionCollection with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteDivisionCollection(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved DivisionCollection is possible'() {
		setup:
			Long divisionCollectionId = createValidDivisionCollection().id
			DivisionCollection divisionCollection = DivisionCollection.findById(divisionCollectionId).find()
		when:
			service.deleteDivisionCollection(divisionCollectionId)
		then:
			divisionCollection != null
			DivisionCollection.findById(divisionCollectionId) == null
	}

	Map invalidData() {

		return ['name': '']
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Division152'
]
		return data
	}

	DivisionCollection createValidDivisionCollection() {
		DivisionCollection divisionCollection = new DivisionCollection(validData())
		divisionCollection.save flush: true, failOnError: true
		return divisionCollection
	}

}

