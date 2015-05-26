package scafmo.constr

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import groovy.transform.TypeCheckingMode
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional(readOnly = true)
class TestOtherSearchService {

	TestOther queryForRead(Long testOtherId) {
		return queryFor(testOtherId, true)
	}

	TestOther queryForWrite(Long testOtherId) {
		return queryFor(testOtherId, false)
	}

	private TestOther queryFor(Long testOtherId, boolean doReadOnly = true) {
		if (!testOtherId || testOtherId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestOther testOther
		if (doReadOnly) {
			testOther = TestOther.read(testOtherId)
		} else {
			testOther = TestOther.get(testOtherId)
		}

		if (!testOther) {
			throw new ResourceNotFound("No TestOther found with Id :[$testOtherId]")
		}
		return testOther
	}

	PagedResultList search(TestOtherSearchCommand cmd, Map pagingParams, boolean doReadOnly = true) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) TestOther.createCriteria()
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
	private void searchCriteria(BuildableCriteria builder, TestOtherSearchCommand cmd) {
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
					// no type defined for testEnum 
					// no type defined for testDate 

					if (searchString.equalsIgnoreCase('false') || searchString.equalsIgnoreCase('true')) {
						eq('booleanNullable', searchString.toBoolean())
					}
				}
			}
			if (cmd.booleanNullable != null) {
				eq('booleanNullable', cmd.booleanNullable)
			}
			if (cmd.testDate != null) {
				Date d = cmd.testDate
				between('testDate', d, d + 1)
			}
//enum - testEnum
			if (cmd.testStringTypes) {
				'in'('testStringType.id', cmd.testStringTypes.collect { (long) it })
			}
			if (cmd.testStringType != null) {
				eq('testStringType.id', cmd.testStringType)
			}
		}
	}
}
