package test

import grails.util.Holders

class Classifier {

    String classname
    String propertyname
    String constant
    String description
	static mapping = {
		sort classname: "asc"
	}
    static constraints = {
		description(size:1..500, blank:false)
		/*classname blank:false, validator: { val -> 
			return (Holders.grailsApplication.getArtefacts("Domain").find{val == it.fullName})?true:false
		}*/
		/*propertyname blank:false, validator: { val, obj -> 
			def domainClass = Holders.grailsApplication.getArtefacts("Domain").find{obj.classname == it.fullName} 
			return (domainClass?.getPersistentProperty(val)) ? true : false
		}*/
		constant(size:1..100, blank:false)
    }
}
