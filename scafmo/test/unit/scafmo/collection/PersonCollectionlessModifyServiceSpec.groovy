package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonCollectionlessModifyService)
@Mock(PersonCollectionless)
class PersonCollectionlessModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollectionless with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updatePersonCollectionless(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating PersonCollectionless with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPersonCollectionless().id
		when:
			PersonCollectionless personCollectionless = service.updatePersonCollectionless(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"age":456,"name":"John454 Doe455"]

		return data
	}

	PersonCollectionless createValidPersonCollectionless(){
		PersonCollectionless personCollectionless = new PersonCollectionless(validData())
		personCollectionless.save flush:true, failOnError: true
		return personCollectionless
	}

}

