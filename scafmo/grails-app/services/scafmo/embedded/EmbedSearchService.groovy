package scafmo.embedded

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class EmbedSearchService {

	Embed queryForRead(Long embedId) {
		return queryFor(embedId, true)
	}

	Embed queryForWrite(Long embedId) {
		return queryFor(embedId, false)
	}

	private Embed queryFor(Long embedId, boolean doReadOnly = true) {
		if (!embedId || embedId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Embed embed
		if (doReadOnly) {
			embed = Embed.read(embedId)
		} else {
			embed = Embed.get(embedId)
		}

		if (!embed) {
			throw new ResourceNotFound("No Embed found with Id :[$embedId]")
		}
		return embed
	}

	PagedResultList search(EmbedSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Embed.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, EmbedSearchCommand cmd) {
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
				}
			}
			if (cmd.acCustomMap != null) {
 				pgJsonHasFieldValue 'acCustomMap', cmd.acCustomMap
			}
			if (cmd.acMap != null) {
				eq('acMap', cmd.acMap)
			}
			if (cmd.acStr) {
				ilike('acStr', cmd.acStr + '%')
			}
			if (cmd.muFileLocation) {
				ilike('muFileLocation', cmd.muFileLocation + '%')
			}
//byte - myFile
			if (cmd.myEmbeddedFieldJsonMap != null) {
				eq('myEmbeddedField.jsonMap', cmd.myEmbeddedFieldJsonMap)
			}
			if (cmd.myEmbeddedFieldMyAc != null) {
				eq('myEmbeddedField.myAc', cmd.myEmbeddedFieldMyAc)
			}
			if (cmd.myEmbeddedFieldStr) {
				ilike('myEmbeddedField.str', cmd.myEmbeddedFieldStr + '%')
			}
		}
	}
}
