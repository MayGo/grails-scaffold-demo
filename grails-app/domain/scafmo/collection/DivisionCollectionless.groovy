package scafmo.collection
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'metaClass')
@EqualsAndHashCode
class DivisionCollectionless {

	String name
	DivisionCollectionless headDivision
	String idTrail
	String nameTrail

	
	static constraints = {
		name nullable:false, blank:false, unique:['headDivision']
		headDivision nullable:true
	}
	

	static mapping = {
		sort "nameTrail"
	}	

	def beforeDelete() {
		PersonCollectionless.executeUpdate("delete PersonCollectionless where division=:division", [division: this])
	}

	def findAllPersons() {
		PersonCollectionless.findAllByDivision(this, [sort:"name"])
	}
	def findAllSubDivisions() {
		DivisionCollectionless.findAllByHeadDivision(this, [sort:"name"])
	}
}