package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonCollectionlessModifyService)
@Mock(PersonCollectionless)
@SuppressWarnings(['DuplicateNumberLiteral'])
class PersonCollectionlessModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating PersonCollectionless with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createPersonCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating PersonCollectionless with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createPersonCollectionless(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating PersonCollectionless with valid data returns PersonCollectionless instance'() {
		setup:
			Map data = validData()
		when:
			PersonCollectionless personCollectionless = service.createPersonCollectionless(data)
		then:
			personCollectionless != null
			personCollectionless.id != null
	}

	void 'Updating PersonCollectionless without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollectionless with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollectionless with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating PersonCollectionless with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPersonCollectionless().id
		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating PersonCollectionless with valid data returns PersonCollectionless instance'() {

		setup:
			Map data = validData()
			data.id = createValidPersonCollectionless().id
		when:
			PersonCollectionless personCollectionless = service.updatePersonCollectionless(data)
		then:
			personCollectionless != null
			personCollectionless.id == 1
	}

	void 'Deleting PersonCollectionless without id is not possible'() {
		when:
			service.deletePersonCollectionless(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PersonCollectionless with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deletePersonCollectionless(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting PersonCollectionless with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deletePersonCollectionless(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved PersonCollectionless is possible'() {
		setup:
			Long personCollectionlessId = createValidPersonCollectionless().id
			PersonCollectionless personCollectionless = PersonCollectionless.findById(personCollectionlessId).find()
		when:
			service.deletePersonCollectionless(personCollectionlessId)
		then:
			personCollectionless != null
			PersonCollectionless.findById(personCollectionlessId) == null
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

	PersonCollectionless createValidPersonCollectionless() {
		PersonCollectionless personCollectionless = new PersonCollectionless(validData())
		personCollectionless.save flush: true, failOnError: true
		return personCollectionless
	}

}

