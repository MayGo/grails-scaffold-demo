package scafmo.embedded

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(EmbedModifyService)
@Mock(Embed)
@SuppressWarnings(['DuplicateNumberLiteral'])
class EmbedModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Embed with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createEmbed(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Embed with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createEmbed(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Embed with valid data returns Embed instance'() {
		setup:
			Map data = validData()
		when:
			Embed embed = service.createEmbed(data)
		then:
			embed != null
			embed.id != null
	}

	void 'Updating Embed without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateEmbed(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Embed with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateEmbed(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Embed with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateEmbed(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Embed with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidEmbed().id
		when:
			service.updateEmbed(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Embed with valid data returns Embed instance'() {

		setup:
			Map data = validData()
			data.id = createValidEmbed().id
		when:
			Embed embed = service.updateEmbed(data)
		then:
			embed != null
			embed.id == 1
	}

	void 'Deleting Embed without id is not possible'() {
		when:
			service.deleteEmbed(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Embed with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteEmbed(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Embed with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteEmbed(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Embed is possible'() {
		setup:
			Long embedId = createValidEmbed().id
			Embed embed = Embed.findById(embedId).find()
		when:
			service.deleteEmbed(embedId)
		then:
			embed != null
			Embed.findById(embedId) == null
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
  'acCustomMap':  [
    'mykey':  'myvalue'
  ],
  'acMap':  [
    'mykey':  'myvalue'
  ],
  'acStr':  'Blank 2',
  'muFileLocation':  'myfile.txt',
  'myFile':  
  [
    116,
    101,
    115,
    116,
    49
  ]
]
		return data
	}

	Embed createValidEmbed() {
		Embed embed = new Embed(validData())
		embed.save flush: true, failOnError: true
		return embed
	}

}

