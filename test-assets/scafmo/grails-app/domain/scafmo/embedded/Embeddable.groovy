package scafmo.embedded

class Embeddable {

	Map myAc
	Map jsonMap
	String str

    static constraints = {
		myAc nullable:true, format: 'testString', widget: 'autocomplete'
		str nullable:true
		jsonMap nullable:true
    }
}
