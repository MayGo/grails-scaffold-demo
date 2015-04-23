package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(TagSearchService)
@Mock(Tag)
class TagSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Tag without id is not possible'() {

		when:
			service.queryForTag(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Tag with illegal id is not possible'() {
		when:
			service.queryForTag(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Tag with fictional id is not possible'() {
		when:
			service.queryForTag(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Tag with valid id returns Tag instance'() {

		setup:
			Long tagId = createValidTag().id
		when:
			Tag tag = service.queryForTag(tagId)
		then:
			tag != null
			tag.id == 1
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'name':  'Work Tag 152'
]
		return data
	}

	Tag createValidTag() {
		Tag tag = new Tag(validData())
		tag.save flush: true, failOnError: true
		return tag
	}
}

