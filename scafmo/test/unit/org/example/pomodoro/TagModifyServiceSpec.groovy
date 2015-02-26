package org.example.pomodoro

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(TagModifyService)
@Mock(Tag)
class TagModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateTag(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Tag with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateTag(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Tag with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidTag().id
		when:
			Tag tag = service.updateTag(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"name":"Work Tag 152"]

		return data
	}

	Tag createValidTag(){
		Tag tag = new Tag(validData())
		tag.save flush:true, failOnError: true
		return tag
	}

}

