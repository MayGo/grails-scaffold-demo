package scafmo.embedded

class Embeddable {

	Map myAc
	String str

    static constraints = {
		myAc nullable:true, format: 'testString', widget: 'autocomplete'
		str nullable:true
    }
}
