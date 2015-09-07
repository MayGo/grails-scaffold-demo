package scafmo.embedded

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class EmbeddableSearchService {

	Embeddable queryForRead(Long embeddableId) {
		return queryFor(embeddableId, true)
	}

	Embeddable queryForWrite(Long embeddableId) {
		return queryFor(embeddableId, false)
	}

	private Embeddable queryFor(Long embeddableId, boolean doReadOnly = true) {
		if (!embeddableId || embeddableId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Embeddable embeddable
		if (doReadOnly) {
			embeddable = Embeddable.read(embeddableId)
		} else {
			embeddable = Embeddable.get(embeddableId)
		}

		if (!embeddable) {
			throw new ResourceNotFound("No Embeddable found with Id :[$embeddableId]")
		}
		return embeddable
	}

	PagedResultList search(EmbeddableSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Embeddable.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, EmbeddableSearchCommand cmd) {
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
			if (cmd.myAc != null) {
				eq('myAc', cmd.myAc)
			}
			if (cmd.str) {
				ilike('str', cmd.str + '%')
			}
		}
	}
}
