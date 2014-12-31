package scafmo.collection
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'metaClass')
@EqualsAndHashCode
class DivisionCollection {

	String name
	DivisionCollection headDivision

	static hasMany = [ persons : PersonCollection ]
	
	static constraints = {
		name nullable:false, blank:false, unique:['headDivision']
		headDivision nullable:true
	}
	

	static mapping = {
		sort "name"
		persons sort:"name", cascade: "all-delete-orphan"
	}	

}