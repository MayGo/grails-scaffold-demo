package org.example.pomodoro

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class TagSearchService {

	Tag queryForRead(Long tagId) {
		return queryFor(tagId, true)
	}

	Tag queryForWrite(Long tagId) {
		return queryFor(tagId, false)
	}

	private Tag queryFor(Long tagId, boolean doReadOnly = true) {
		if (!tagId || tagId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Tag tag
		if (doReadOnly) {
			tag = Tag.read(tagId)
		} else {
			tag = Tag.get(tagId)
		}

		if (!tag) {
			throw new ResourceNotFound("No Tag found with Id :[$tagId]")
		}
		return tag
	}

	PagedResultList search(TagSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Tag.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
			readOnly(doReadOnly)
		}

		return results
	}

	// TODO: Refactor and cleanup code so Codenarc check passes dynamic pgJsonHasFieldValue
	@SuppressWarnings(['AbcMetric', 'CyclomaticComplexity', 'MethodSize'])
	@GrailsCompileStatic(TypeCheckingMode.SKIP) // We want to use dynamically added criterias, eg: pgJsonHasFieldValue
	private void searchCriteria(BuildableCriteria builder, TagSearchCommand cmd) {
		String searchString = cmd.searchString
		builder.with {
			if (cmd.id) {
				eq('id', cmd.id)
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
			if (cmd.name) {
				ilike('name', cmd.name + '%')
			}
		}
	}
}
