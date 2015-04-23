package test

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound

@TestFor(ClassifierSearchService)
@Mock(Classifier)
class ClassifierSearchServiceSpec extends Specification {

	static final long ILLEGAL_ID = -1L
	static final long FICTIONAL_ID = 99999999L

	void 'Quering Classifier without id is not possible'() {

		when:
			service.queryForClassifier(null)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Classifier with illegal id is not possible'() {
		when:
			service.queryForClassifier(ILLEGAL_ID)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Quering Classifier with fictional id is not possible'() {
		when:
			service.queryForClassifier(FICTIONAL_ID)
		then:
			thrown(ResourceNotFound)
	}

	void 'Quering Classifier with valid id returns Classifier instance'() {

		setup:
			Long classifierId = createValidClassifier().id
		when:
			Classifier classifier = service.queryForClassifier(classifierId)
		then:
			classifier != null
			classifier.id == 1
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

