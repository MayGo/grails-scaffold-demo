package scafmo.constr

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class TestNumberSearchService {

	TestNumber queryForRead(Long testNumberId) {
		return queryFor(testNumberId, true)
	}

	TestNumber queryForWrite(Long testNumberId) {
		return queryFor(testNumberId, false)
	}

	private TestNumber queryFor(Long testNumberId, boolean doReadOnly = true) {
		if (!testNumberId || testNumberId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestNumber testNumber
		if (doReadOnly) {
			testNumber = TestNumber.read(testNumberId)
		} else {
			testNumber = TestNumber.get(testNumberId)
		}

		if (!testNumber) {
			throw new ResourceNotFound("No TestNumber found with Id :[$testNumberId]")
		}
		return testNumber
	}

	PagedResultList search(TestNumberSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) TestNumber.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, TestNumberSearchCommand cmd) {
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

					if (searchString.isLong()) {
						eq('longNr', searchString.toLong())
					}

					if (searchString.isInteger()) {
						eq('integerNrUnique', searchString.toInteger())
					}

					if (searchString.isInteger()) {
						eq('integerNrRange', searchString.toInteger())
					}
				}
			}
			if (cmd.doubleNr != null) {
				eq('doubleNr', cmd.doubleNr)
			}
			if (cmd.floatNr != null) {
				eq('floatNr', cmd.floatNr)
			}
			if (cmd.floatNrScale != null) {
				eq('floatNrScale', cmd.floatNrScale)
			}
			if (cmd.integerNr != null) {
				eq('integerNr', cmd.integerNr)
			}
//inList - integerNrInList
			if (cmd.integerNrMax != null) {
				eq('integerNrMax', cmd.integerNrMax)
			}
			if (cmd.integerNrMin != null) {
				eq('integerNrMin', cmd.integerNrMin)
			}
			if (cmd.integerNrNotEqual != null) {
				eq('integerNrNotEqual', cmd.integerNrNotEqual)
			}
			if (cmd.integerNrRange != null) {
				eq('integerNrRange', cmd.integerNrRange)
			}
			if (cmd.integerNrUnique != null) {
				eq('integerNrUnique', cmd.integerNrUnique)
			}
			if (cmd.longNr != null) {
				eq('longNr', cmd.longNr)
			}
		}
	}
}
