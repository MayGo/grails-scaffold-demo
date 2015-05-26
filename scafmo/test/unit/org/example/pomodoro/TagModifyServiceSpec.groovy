package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TagModifyService)
@Mock(Tag)
@SuppressWarnings(['DuplicateNumberLiteral'])
class TagModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Tag with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createTag(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Tag with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createTag(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Tag with valid data returns Tag instance'() {
		setup:
			Map data = validData()
		when:
			Tag tag = service.createTag(data)
		then:
			tag != null
			tag.id != null
	}

	void 'Updating Tag without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateTag(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Tag with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateTag(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Tag with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateTag(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Tag with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTag().id
		when:
			service.updateTag(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Tag with valid data returns Tag instance'() {

		setup:
			Map data = validData()
			data.id = createValidTag().id
		when:
			Tag tag = service.updateTag(data)
		then:
			tag != null
			tag.id == 1
	}

	void 'Deleting Tag without id is not possible'() {
		when:
			service.deleteTag(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Tag with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteTag(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Tag with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteTag(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Tag is possible'() {
		setup:
			Long tagId = createValidTag().id
			Tag tag = Tag.findById(tagId).find()
		when:
			service.deleteTag(tagId)
		then:
			tag != null
			Tag.findById(tagId) == null
	}

	Map invalidData() {

		return ['name': null]
	}

	// TODO: Refactor and cleanup code so Codenarc check passes
	@SuppressWarnings(['MethodSize'])
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

