package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(PersonCollectionModifyService)
@Mock(PersonCollection)
class PersonCollectionModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updatePersonCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating PersonCollection with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updatePersonCollection(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating PersonCollection with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidPersonCollection().id
		when:
			PersonCollection personCollection = service.updatePersonCollection(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"age":456,"name":"John454 Doe455"]

		return data
	}

	PersonCollection createValidPersonCollection(){
		PersonCollection personCollection = new PersonCollection(validData())
		personCollection.save flush:true, failOnError: true
		return personCollection
	}

}

