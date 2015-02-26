package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(DivisionCollectionModifyService)
@Mock(DivisionCollection)
class DivisionCollectionModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateDivisionCollection(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollection with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateDivisionCollection(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating DivisionCollection with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidDivisionCollection().id
		when:
			DivisionCollection divisionCollection = service.updateDivisionCollection(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"name":"Division152"]

		return data
	}

	DivisionCollection createValidDivisionCollection(){
		DivisionCollection divisionCollection = new DivisionCollection(validData())
		divisionCollection.save flush:true, failOnError: true
		return divisionCollection
	}

}

