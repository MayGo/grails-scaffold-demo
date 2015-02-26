package test

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class ClassifierModifyService {
	def grailsWebDataBinder

	Classifier createClassifier(Map data){
		Classifier classifier = Classifier.newInstance()
		return createOrUpdate(classifier, data)
	}

	Classifier updateClassifier(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Classifier classifier = queryForClassifier(data.id)

		if(!classifier){
			throw new ResourceNotFound("No Classifier found with Id :[${data.id}]")
		}

		return createOrUpdate(classifier, data)
	}

	Classifier createOrUpdate(Classifier classifier, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind classifier, data as SimpleMapDataBindingSource

		classifier.save flush: true, failOnError: true

		return classifier
	}

	Classifier queryForClassifier(long id){
		return Classifier.get(id)
	}
}
