package scafmo.embedded
import types.CustomMapType
import types.CustomMap

class Embed {

   	String acStr
	Map acMap
	CustomMap acCustomMap
	byte[] myFile
	String muFileLocation

	static constraints = {
		acStr nullable:true, format: 'testString', widget: 'autocomplete'
		acMap nullable:true, format: 'testString', widget: 'autocomplete'
		acCustomMap nullable:true, format: 'testString', widget: 'autocomplete', type: CustomMapType
		myEmbeddedField nullable:true
		myFile nullable:true
		muFileLocation nullable:true, widget:'upload-many'
    }

	Embeddable myEmbeddedField

	static embedded = ['myEmbeddedField']
}
