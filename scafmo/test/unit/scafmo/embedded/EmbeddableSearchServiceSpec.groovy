package scafmo.embedded

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(EmbeddableSearchService)
@Mock(Embeddable)
class EmbeddableSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Embeddable without id is not possible'() {

		when:
			service.queryForRead(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Embeddable with illegal id is not possible'() {
		when:
			service.queryForRead(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Embeddable with fictional id is not possible'() {
		when:
			service.queryForRead(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Embeddable with valid id returns Embeddable instance'() {

		setup:
			Long embeddableId = createValidEmbeddable().id
		when:
			Embeddable embeddable = service.queryForRead(embeddableId)
		then:
			embeddable != null
			embeddable.id == 1
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
	Map validData() {

		Map data = []
		return data
	}

	Embeddable createValidEmbeddable() {
		Embeddable embeddable = new Embeddable(validData())
		embeddable.save flush: true, failOnError: true
		return embeddable
	}
}

