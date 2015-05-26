package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class TagModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Tag createTag(Map data) {
		Tag tag = Tag.newInstance()
		return createOrUpdate(tag, data)
	}

	Tag updateTag(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Tag tag = Tag.where { id == objId }.find()

		if (!tag) {
			throw new ResourceNotFound("No Tag found with Id :[$objId]")
		}

		return createOrUpdate(tag, data)
	}

	Tag createOrUpdate(Tag tag, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind tag, data as SimpleMapDataBindingSource

		tag.save failOnError: true

		return tag
	}

	void deleteTag(Long tagId) {
		if (!tagId || tagId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Tag tag = Tag.where { id == tagId }.find()

		if (!tag) {
			throw new ResourceNotFound("No Tag found with Id:$tagId")
		}

		tag.delete()
	}
}

