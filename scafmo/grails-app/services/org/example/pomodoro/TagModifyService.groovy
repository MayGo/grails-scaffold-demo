package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TagModifyService {
	def grailsWebDataBinder

	Tag createTag(Map data) {
		Tag tag = Tag.newInstance()
		return createOrUpdate(tag, data)
	}

	Tag updateTag(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Tag tag = Tag.where { id == data.id }.find()

		if (!tag) {
			throw new ResourceNotFound("No Tag found with Id :[${data.id}]")
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

