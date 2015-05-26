package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonCollectionModifyService)
@Mock(PersonCollection)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PersonCollectionModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating PersonCollection with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createPersonCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating PersonCollection with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createPersonCollection(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating PersonCollection with valid data returns PersonCollection instance'() {
		setup:
			Map data = validData()
		when:
			PersonCollection personCollection = service.createPersonCollection(data)
		then:
			personCollection != null
			personCollection.id != null
	}

	void 'Updating PersonCollection without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updatePersonCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollection with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updatePersonCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollection with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updatePersonCollection(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating PersonCollection with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPersonCollection().id
		when:
			service.updatePersonCollection(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating PersonCollection with valid data returns PersonCollection instance'() {

		setup:
			Map data = validData()
			data.id = createValidPersonCollection().id
		when:
			PersonCollection personCollection = service.updatePersonCollection(data)
		then:
			personCollection != null
			personCollection.id == 1
	}

	void 'Deleting PersonCollection without id is not possible'() {
		when:
			service.deletePersonCollection(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PersonCollection with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deletePersonCollection(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PersonCollection with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deletePersonCollection(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved PersonCollection is possible'() {
		setup:
			Long personCollectionId = createValidPersonCollection().id
			PersonCollection personCollection = PersonCollection.findById(personCollectionId).find()
		when:
			service.deletePersonCollection(personCollectionId)
		then:
			personCollection != null
			PersonCollection.findById(personCollectionId) == null
	}

	Map invalidData() {

		return ['age': null,
 'name': '']
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'age':  456,
  'name':  'John454 Doe455'
]
		return data
	}

	PersonCollection createValidPersonCollection() {
		PersonCollection personCollection = new PersonCollection(validData())
		personCollection.save flush: true, failOnError: true
		return personCollection
	}

}

