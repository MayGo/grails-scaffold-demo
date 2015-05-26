package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class TestOtherModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	TestOther createTestOther(Map data) {
		TestOther testOther = TestOther.newInstance()
		return createOrUpdate(testOther, data)
	}

	TestOther updateTestOther(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		TestOther testOther = TestOther.where { id == objId }.find()

		if (!testOther) {
			throw new ResourceNotFound("No TestOther found with Id :[$objId]")
		}

		return createOrUpdate(testOther, data)
	}

	TestOther createOrUpdate(TestOther testOther, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind testOther, data as SimpleMapDataBindingSource

		testOther.save failOnError: true

		return testOther
	}

	void deleteTestOther(Long testOtherId) {
		if (!testOtherId || testOtherId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestOther testOther = TestOther.where { id == testOtherId }.find()

		if (!testOther) {
			throw new ResourceNotFound("No TestOther found with Id:$testOtherId")
		}

		testOther.delete()
	}
}

