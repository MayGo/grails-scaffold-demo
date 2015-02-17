package scafmo.collection
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


@ToString(includeNames = true, includeFields = true, excludes = 'metaClass')
@EqualsAndHashCode
class PersonCollectionless {

	String name
	Integer age
	
	DivisionCollectionless division

	static mapping = { sort "name" }

	static constraints = {
		name nullable:false, blank:false
		age nullable:false
		division nullable:true
	}
}