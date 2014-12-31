package scafmo.constr

class TestString {

   	String blankStr
	String creditCardStr
	String emailStr
	String inListStr
	String matchesStr
	String maxSizeStr
	String minSizeStr
	String sizeStr
	String notEqualStr
	String uniqueStr
	String urlStr
	
	
    static constraints = {
		blankStr nullable:true, blank:false
		creditCardStr nullable:true, creditCard:true
		emailStr nullable:true, email:true
		inListStr nullable:true, inList: ["test1", "test2", "test3"]
		matchesStr nullable:true, matches: "[a-zA-Z]+"
		maxSizeStr nullable:true, maxSize: 5
		minSizeStr nullable:true, minSize: 2
		sizeStr nullable:true, size:1..100
		notEqualStr nullable:true, notEqual:"test" 
		uniqueStr nullable:true, unique:true
		urlStr nullable:true, url:true
    }
}
