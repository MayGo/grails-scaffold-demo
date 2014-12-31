package scafmo.collection

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


@ToString(includeNames = true, includeFields = true, excludes = 'metaClass')
@EqualsAndHashCode
class PersonCollection {

	String name
	Integer age
	
	static belongsTo = [ division : DivisionCollection  ]
	

	static mapping = { sort "name" }

	static constraints = {
		name nullable:false, blank:false
		age nullable:false
		division nullable:true
	}
}