package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class TagSearchService {

	Tag queryForTag(Long tagId) {
		if (!tagId || tagId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Tag tag = Tag.where { id == tagId }.find()
		if (!tag) {
			throw new ResourceNotFound("No Tag found with Id :[$tagId]")
		}
		return tag
	}

	PagedResultList search(Map params) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Tag.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: params.offset,
				max: params.max,
				order: params.order,
				sort: params.sort
		) {
			searchCriteria criteriaBuilder, params
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, Map params) {
		String searchString = params.searchString
		JSONElement filter = params.filter ? JSON.parse(params.filter.toString()) : new JSONObject()

		builder.with {
			//readOnly true

			if (filter['id']) {
				eq('id', filter['id'].toString().toLong())
			}

			if (searchString) {
				or {
					eq('id', -1L)

					if (searchString.isLong()) {
						eq('id', searchString.toLong())
					}
					like('name', searchString + '%')
				}
			}
			if (filter['name']) {
				ilike('name', "${filter['name']}%")
			}
		}
	}
}
