package scafmo.constr

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class TestStringSearchService {

	TestString queryForRead(Long testStringId) {
		return queryFor(testStringId, true)
	}

	TestString queryForWrite(Long testStringId) {
		return queryFor(testStringId, false)
	}

	private TestString queryFor(Long testStringId, boolean doReadOnly = true) {
		if (!testStringId || testStringId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestString testString
		if (doReadOnly) {
			testString = TestString.read(testStringId)
		} else {
			testString = TestString.get(testStringId)
		}

		if (!testString) {
			throw new ResourceNotFound("No TestString found with Id :[$testStringId]")
		}
		return testString
	}

	PagedResultList search(TestStringSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) TestString.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, TestStringSearchCommand cmd) {
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
					like('urlStr', searchString + '%')
					like('uniqueStr', searchString + '%')
					like('sizeStr', searchString + '%')
				}
			}
			if (cmd.blankStr) {
				ilike('blankStr', cmd.blankStr + '%')
			}
			if (cmd.creditCardStr) {
				ilike('creditCardStr', cmd.creditCardStr + '%')
			}
			if (cmd.emailStr) {
				ilike('emailStr', cmd.emailStr + '%')
			}
//inList - inListStr
			if (cmd.matchesStr) {
				ilike('matchesStr', cmd.matchesStr + '%')
			}
			if (cmd.maxSizeStr) {
				ilike('maxSizeStr', cmd.maxSizeStr + '%')
			}
			if (cmd.minSizeStr) {
				ilike('minSizeStr', cmd.minSizeStr + '%')
			}
			if (cmd.notEqualStr) {
				ilike('notEqualStr', cmd.notEqualStr + '%')
			}
			if (cmd.sizeStr) {
				ilike('sizeStr', cmd.sizeStr + '%')
			}
			if (cmd.textareaStr) {
				ilike('textareaStr', cmd.textareaStr + '%')
			}
			if (cmd.uniqueStr) {
				ilike('uniqueStr', cmd.uniqueStr + '%')
			}
			if (cmd.urlStr) {
				ilike('urlStr', cmd.urlStr + '%')
			}
		}
	}
}
