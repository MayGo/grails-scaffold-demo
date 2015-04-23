package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TestNumberModifyService {
	def grailsWebDataBinder

	TestNumber createTestNumber(Map data) {
		TestNumber testNumber = TestNumber.newInstance()
		return createOrUpdate(testNumber, data)
	}

	TestNumber updateTestNumber(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestNumber testNumber = TestNumber.where { id == data.id }.find()

		if (!testNumber) {
			throw new ResourceNotFound("No TestNumber found with Id :[${data.id}]")
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

