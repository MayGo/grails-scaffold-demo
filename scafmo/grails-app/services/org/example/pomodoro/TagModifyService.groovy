package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TagModifyService {
	def grailsWebDataBinder

	Tag createTag(Map data){
		Tag tag = Tag.newInstance()
		return createOrUpdate(tag, data)
	}

	Tag updateTag(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Tag tag = queryForTag(data.id)

		if(!tag){
			throw new ResourceNotFound("No Tag found with Id :[${data.id}]")
		}

		return createOrUpdate(tag, data)
	}

	Tag createOrUpdate(Tag tag, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind tag, data as SimpleMapDataBindingSource

		tag.save flush: true, failOnError: true

		return tag
	}

	Tag queryForTag(long id){
		return Tag.get(id)
	}
}
