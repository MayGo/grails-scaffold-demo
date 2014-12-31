package scafmo.constr

class TestNumber {
	
	Double doubleNr
	Integer integerNr
	Float floatNr
	Long longNr
	
	Integer integerNrInList
	Integer integerNrNotEqual
	Integer integerNrRange
	Integer integerNrUnique
	Integer integerNrMin
	Integer integerNrMax

	
	
	Float floatNrScale
	
    static constraints = {
		doubleNr nullable:true
		integerNr nullable:true
		floatNr nullable:true
		longNr nullable:true
		
		integerNrInList nullable:true, inList: [1,2,3]
		integerNrNotEqual nullable:true, notEqual: 1
		integerNrRange nullable:true, range: 18..65
		integerNrUnique nullable:true, unique:true
		integerNrMin nullable:true, min: 2
		integerNrMax nullable:true, max: 3
		floatNrScale nullable:true, scale: 2
    }
}
