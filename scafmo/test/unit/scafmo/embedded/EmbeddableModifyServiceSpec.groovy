package scafmo.embedded

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(EmbeddableModifyService)
@Mock(Embeddable)
@SuppressWarnings(['DuplicateNumberLiteral'])
class EmbeddableModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Embeddable with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createEmbeddable(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Embeddable with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createEmbeddable(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Embeddable with valid data returns Embeddable instance'() {
		setup:
			Map data = validData()
		when:
			Embeddable embeddable = service.createEmbeddable(data)
		then:
			embeddable != null
			embeddable.id != null
	}

	void 'Updating Embeddable without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateEmbeddable(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Embeddable with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateEmbeddable(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Embeddable with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateEmbeddable(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Embeddable with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidEmbeddable().id
		when:
			service.updateEmbeddable(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Embeddable with valid data returns Embeddable instance'() {

		setup:
			Map data = validData()
			data.id = createValidEmbeddable().id
		when:
			Embeddable embeddable = service.updateEmbeddable(data)
		then:
			embeddable != null
			embeddable.id == 1
	}

	void 'Deleting Embeddable without id is not possible'() {
		when:
			service.deleteEmbeddable(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Embeddable with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteEmbeddable(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Embeddable with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteEmbeddable(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Embeddable is possible'() {
		setup:
			Long embeddableId = createValidEmbeddable().id
			Embeddable embeddable = Embeddable.findById(embeddableId).find()
		when:
			service.deleteEmbeddable(embeddableId)
		then:
			embeddable != null
			Embeddable.findById(embeddableId) == null
	}

	Map invalidData() {

		return ['foo': 'Sadisfy empty data exception']
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'jsonMap':  [
    'mykey':  'myvalue'
  ],
  'myAc':  [
    'mykey':  'myvalue'
  ],
  'str':  'Blank 153'
]
		return data
	}

	Embeddable createValidEmbeddable() {
		Embeddable embeddable = new Embeddable(validData())
		embeddable.save flush: true, failOnError: true
		return embeddable
	}

}

