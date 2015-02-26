package scafmo.constr

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class TestStringModifyService {
	def grailsWebDataBinder

	TestString createTestString(Map data){
		TestString testString = TestString.newInstance()
		return createOrUpdate(testString, data)
	}

	TestString updateTestString(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		TestString testString = queryForTestString(data.id)

		if(!testString){
			throw new ResourceNotFound("No TestString found with Id :[${data.id}]")
		}

		return createOrUpdate(testString, data)
	}

	TestString createOrUpdate(TestString testString, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind testString, data as SimpleMapDataBindingSource

		testString.save flush: true, failOnError: true

		return testString
	}

	TestString queryForTestString(long id){
		return TestString.get(id)
	}
}
