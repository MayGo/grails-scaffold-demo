package scafmo.collection

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(DivisionCollectionlessModifyService)
@Mock(DivisionCollectionless)
class DivisionCollectionlessModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating DivisionCollectionless with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateDivisionCollectionless(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating DivisionCollectionless with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidDivisionCollectionless().id
		when:
			DivisionCollectionless divisionCollectionless = service.updateDivisionCollectionless(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"name":"Division152"]

		return data
	}

	DivisionCollectionless createValidDivisionCollectionless(){
		DivisionCollectionless divisionCollectionless = new DivisionCollectionless(validData())
		divisionCollectionless.save flush:true, failOnError: true
		return divisionCollectionless
	}

}

