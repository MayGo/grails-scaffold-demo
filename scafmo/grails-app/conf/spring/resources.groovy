// Place your Spring DSL code here
beans = {

	for (domainClass in application.domainClasses) {
		"json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	}





		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)

		nonAuthFilter(defpackage.NonAuthenticationFilter)
		
		if(grails.util.Environment.current == grails.util.Environment.TEST && !grails.util.Holders.config.functionalTest.userName) {
			userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
		}


}
