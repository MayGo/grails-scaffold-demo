package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TestOtherModifyService {
	def grailsWebDataBinder

	TestOther createTestOther(Map data){
		TestOther testOther = TestOther.newInstance()
		return createOrUpdate(testOther, data)
	}

	TestOther updateTestOther(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		TestOther testOther = queryForTestOther(data.id)

		if(!testOther){
			throw new ResourceNotFound("No TestOther found with Id :[${data.id}]")
		}

		return createOrUpdate(testOther, data)
	}

	TestOther createOrUpdate(TestOther testOther, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind testOther, data as SimpleMapDataBindingSource

		testOther.save flush: true, failOnError: true

		return testOther
	}

	TestOther queryForTestOther(long id){
		return TestOther.get(id)
	}
}
