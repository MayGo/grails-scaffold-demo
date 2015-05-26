package test

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class ClassifierModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Classifier createClassifier(Map data) {
		Classifier classifier = Classifier.newInstance()
		return createOrUpdate(classifier, data)
	}

	Classifier updateClassifier(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Classifier classifier = Classifier.where { id == objId }.find()

		if (!classifier) {
			throw new ResourceNotFound("No Classifier found with Id :[$objId]")
		}

		return createOrUpdate(classifier, data)
	}

	Classifier createOrUpdate(Classifier classifier, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind classifier, data as SimpleMapDataBindingSource

		classifier.save failOnError: true

		return classifier
	}

	void deleteClassifier(Long classifierId) {
		if (!classifierId || classifierId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Classifier classifier = Classifier.where { id == classifierId }.find()

		if (!classifier) {
			throw new ResourceNotFound("No Classifier found with Id:$classifierId")
		}

		classifier.delete()
	}
}

