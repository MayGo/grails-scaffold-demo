package test

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(ClassifierModifyService)
@Mock(Classifier)
class ClassifierModifyServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Creating Classifier with no data is not possible'() {
		setup:
			Map data = [:]
		when:
			service.createClassifier(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Creating Classifier with invalid data is not possible'() {
		setup:
			Map data = invalidData()
		when:
			service.createClassifier(data)
		then:
			thrown(ValidationException)
	}

	void 'Creating Classifier with valid data returns Classifier instance'() {
		setup:
			Map data = validData()
		when:
			Classifier classifier = service.createClassifier(data)
		then:
			classifier != null
			classifier.id != null
	}

	void 'Updating Classifier without id is not possible'() {

		setup:
			Map data = [:]
		when:
			service.updateClassifier(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Classifier with illegal id is not possible'() {

		setup:
			Map data = [id: ILLEGAL_ID]
		when:
			service.updateClassifier(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Classifier with fictional id is not possible'() {

		setup:
			Map data = [id: FICTIONAL_ID]

		when:
			service.updateClassifier(data)
		then:
			thrown(ResourceNotFound)
	}

	void 'Updating Classifier with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidClassifier().id
		when:
			service.updateClassifier(data)
		then:
			thrown(ValidationException)
	}

	void 'Updating Classifier with valid data returns Classifier instance'() {

		setup:
			Map data = validData()
			data.id = createValidClassifier().id
		when:
			Classifier classifier = service.updateClassifier(data)
		then:
			classifier != null
			classifier.id == 1
	}

	void 'Deleting Classifier without id is not possible'() {
		when:
			service.deleteClassifier(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Classifier with illegal id is not possible'() {

		setup:
			long id = ILLEGAL_ID
		when:
			service.deleteClassifier(id)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Deleting Classifier with fictional id is not possible'() {

		setup:
			long id = FICTIONAL_ID
		when:
			service.deleteClassifier(id)
		then:
			thrown(ResourceNotFound)
	}

	void 'Deleting saved Classifier is possible'() {
		setup:
			Long classifierId = createValidClassifier().id
			Classifier classifier = Classifier.findById(classifierId).find()
		when:
			service.deleteClassifier(classifierId)
		then:
			classifier != null
			Classifier.findById(classifierId) == null
	}

	Map invalidData() {

		return ['classname': null,
 'constant': null,
 'description': null,
 'propertyname': null]
	}

	Map validData() {

		Map data = [
  'id':  null,
  'version':  null,
  'classname':  'classname',
  'constant':  'constant',
  'description':  'description',
  'propertyname':  'propertyname'
]
		return data
	}

	Classifier createValidClassifier() {
		Classifier classifier = new Classifier(validData())
		classifier.save flush: true, failOnError: true
		return classifier
	}

}

