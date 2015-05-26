package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class TestStringModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	TestString createTestString(Map data) {
		TestString testString = TestString.newInstance()
		return createOrUpdate(testString, data)
	}

	TestString updateTestString(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		TestString testString = TestString.where { id == objId }.find()

		if (!testString) {
			throw new ResourceNotFound("No TestString found with Id :[$objId]")
		}

		return createOrUpdate(testString, data)
	}

	TestString createOrUpdate(TestString testString, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind testString, data as SimpleMapDataBindingSource

		testString.save failOnError: true

		return testString
	}

	void deleteTestString(Long testStringId) {
		if (!testStringId || testStringId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestString testString = TestString.where { id == testStringId }.find()

		if (!testString) {
			throw new ResourceNotFound("No TestString found with Id:$testStringId")
		}

		testString.delete()
	}
}

