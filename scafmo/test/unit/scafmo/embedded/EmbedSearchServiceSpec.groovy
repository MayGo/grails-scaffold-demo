package scafmo.embedded

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(EmbedSearchService)
@Mock(Embed)
class EmbedSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Embed without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Embed with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Embed with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Embed with valid id returns Embed instance'() {

		setup:
			Long embedId = createValidEmbed().id
		when:
			Embed embed = service.queryForRead(embedId)
		then:
			embed != null
			embed.id == 1
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

