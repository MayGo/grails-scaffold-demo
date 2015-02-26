package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TestNumberModifyService {
	def grailsWebDataBinder

	TestNumber createTestNumber(Map data){
		TestNumber testNumber = TestNumber.newInstance()
		return createOrUpdate(testNumber, data)
	}

	TestNumber updateTestNumber(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		TestNumber testNumber = queryForTestNumber(data.id)

		if(!testNumber){
			throw new ResourceNotFound("No TestNumber found with Id :[${data.id}]")
		}

		return createOrUpdate(testNumber, data)
	}

	TestNumber createOrUpdate(TestNumber testNumber, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind testNumber, data as SimpleMapDataBindingSource

		testNumber.save flush: true, failOnError: true

		return testNumber
	}

	TestNumber queryForTestNumber(long id){
		return TestNumber.get(id)
	}
}
