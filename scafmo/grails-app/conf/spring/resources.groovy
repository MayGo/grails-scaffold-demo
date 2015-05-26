// Place your Spring DSL code here
beans = {

		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		/* CollectionRenderer not working in grails version > 2.4.3

	for (domainClass in application.domainClasses) {
		"json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	}
		*/
		nonAuthFilter(defpackage.NonAuthenticationFilter)
		
		if(grails.util.Environment.current == grails.util.Environment.TEST && !grails.util.Holders.config.functionalTest.userName) {
			userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
		}


}
