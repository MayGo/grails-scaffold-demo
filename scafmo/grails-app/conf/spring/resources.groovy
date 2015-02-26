// Place your Spring DSL code here
beans = {

		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		
	for (domainClass in application.domainClasses) {
		"json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	}
		
		nonAuthFilter(defpackage.NonAuthenticationFilter)
		
		if(grails.util.Environment.current == grails.util.Environment.TEST && !grails.util.Holders.config.functionalTest.userName) {
			userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
		}


}
