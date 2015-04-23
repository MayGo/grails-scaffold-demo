package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TestOtherModifyService {
	def grailsWebDataBinder

	TestOther createTestOther(Map data) {
		TestOther testOther = TestOther.newInstance()
		return createOrUpdate(testOther, data)
	}

	TestOther updateTestOther(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		TestOther testOther = TestOther.where { id == data.id }.find()

		if (!testOther) {
			throw new ResourceNotFound("No TestOther found with Id :[${data.id}]")
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

