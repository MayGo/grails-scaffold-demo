package test

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Ignore
import spock.lang.Specification
import defpackage.exceptions.ResourceNotFound
import grails.validation.ValidationException

@TestFor(ClassifierModifyService)
@Mock(Classifier)
class ClassifierModifyServiceSpec extends Specification {

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
			Map data = [id:-1L]
		when:
			service.updateClassifier(data)
		then:
			thrown(IllegalArgumentException)
	}

	void 'Updating Classifier with fictional id is not possible'() {

		setup:
			Map data = [id:99999999L]

		when:
			service.updateClassifier(data)
		then:
			thrown(ResourceNotFound)
	}

	@Ignore //TODO: set invalid data first
	void 'Updating Classifier with invalid data is not possible'() {

		setup:
			Map data = invalidData()
			data.id = createValidClassifier().id
		when:
			Classifier classifier = service.updateClassifier(data)
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

	Map invalidData() {
		return ["foo": "bar"]//Sadisfy 'empty data' exception
	}

	Map validData() {
		
		Map data = ["id":null,"version":null,"classname":"classname","constant":"constant","description":"description","propertyname":"propertyname"]

		return data
	}

	Classifier createValidClassifier(){
		Classifier classifier = new Classifier(validData())
		classifier.save flush:true, failOnError: true
		return classifier
	}

}

