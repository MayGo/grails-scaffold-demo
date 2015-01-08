package defpackage

import org.example.pomodoro.*
import org.grails.samples.*
import scafmo.collection.*
import scafmo.constr.*
import scafmo.security.*


class TestDataGeneratorService {
	
	static transactional = false
	
	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

	
	void generate() {
		log.info "Generating test data."
		boolean generateTestData = true
		
		Tag.withNewTransaction{
			generateTestData = (Tag.count() == 0)
		}
		if(!generateTestData){
			log.info "Tag count is bigger than 0. Not generating test data."
			return
		}

		(1..100).each{index->
			Tag.withNewTransaction{

				Tag.build()
				Task.build()
				Owner.build()
				Person.build()
				Pet.build(type:PetType.build(), owner:Owner.build())
				PetType.build()
				Speciality.build()
				Vet.build()
				Visit.build(pet:Pet.build())
				DivisionCollection.build()
				DivisionCollectionless.build()
				PersonCollection.build()
				PersonCollectionless.build()
				TestNumber.build()
				TestOther.build()
				TestString.build()
				Role.build()
				User.build()
				UserRole.build(role:Role.build(), user:User.build())

				if (index % 20 == 0) cleanUpGorm()
			}
		}
		log.info "Generated test data."
	}
	
	def cleanUpGorm() {
		log.info "Clean Up Gorm"
		def session = sessionFactory.currentSession
		session.flush()
		session.clear()
		propertyInstanceMap.get().clear()
	}
}
