package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class TestNumberModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	TestNumber createTestNumber(Map data) {
		TestNumber testNumber = TestNumber.newInstance()
		return createOrUpdate(testNumber, data)
	}

	TestNumber updateTestNumber(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		TestNumber testNumber = TestNumber.where { id == objId }.find()

		if (!testNumber) {
			throw new ResourceNotFound("No TestNumber found with Id :[$objId]")
		}

		return createOrUpdate(testNumber, data)
	}

	TestNumber createOrUpdate(TestNumber testNumber, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind testNumber, data as SimpleMapDataBindingSource

		testNumber.save failOnError: true

		return testNumber
	}

	void deleteTestNumber(Long testNumberId) {
		if (!testNumberId || testNumberId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestNumber testNumber = TestNumber.where { id == testNumberId }.find()

		if (!testNumber) {
			throw new ResourceNotFound("No TestNumber found with Id:$testNumberId")
		}

		testNumber.delete()
	}
}

