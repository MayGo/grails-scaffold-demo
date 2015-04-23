package defpackage

import grails.util.Holders
import org.example.pomodoro.Tag
import org.example.pomodoro.Task
import org.grails.samples.Owner
import org.grails.samples.Person
import org.grails.samples.Pet
import org.grails.samples.PetType
import org.grails.samples.Speciality
import org.grails.samples.Vet
import org.grails.samples.Visit
import scafmo.collection.DivisionCollection
import scafmo.collection.DivisionCollectionless
import scafmo.collection.PersonCollection
import scafmo.collection.PersonCollectionless
import scafmo.constr.TestNumber
import scafmo.constr.TestOther
import scafmo.constr.TestString
import scafmo.security.Role
import scafmo.security.User
import scafmo.security.UserRole
import test.Classifier

class TestDataGeneratorService {

	static transactional = false

	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

	static final int GENERATE_N_ITEMS = (Holders.config.generateTestDataAmount)?:150

	void generate() {
		log.info 'Generating test data.'
		boolean generateTestData = true

		Tag.withNewTransaction {
			generateTestData = (Tag.count() == 0)
		}
		if (!generateTestData) {
			log.info 'Tag count is bigger than 0. Not generating test data.'
			return
		}

		(1..GENERATE_N_ITEMS).each { index ->
			Tag.withNewTransaction {

				Tag.build()
				Task.build()
				Owner.build()
				Person.build()
				Pet.build(type: PetType.build(), owner: Owner.build())
				PetType.build()
				Speciality.build()
				Vet.build()
				Visit.build(pet: Pet.build())
				DivisionCollection.build()
				DivisionCollectionless.build()
				PersonCollection.build()
				PersonCollectionless.build()
				TestNumber.build()
				TestOther.build()
				TestString.build()
				Role.build()
				User.build()
				UserRole.build(role: Role.build(), user: User.build())
				Classifier.build()

				if (index % 20 == 0) {
					cleanUpGorm()
				}
			}
		}
		log.info 'Generated test data.'
	}

	def cleanUpGorm() {
		log.info 'Clean Up Gorm'
		def session = sessionFactory.currentSession
		session.flush()
		session.clear()
		propertyInstanceMap.get().clear()
	}
}
