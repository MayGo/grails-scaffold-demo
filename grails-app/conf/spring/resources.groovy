
beans = {

	customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		
	for (domainClass in application.domainClasses) {
	    "json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	}
	nonAuthFilter(defpackage.NonAuthenticationFilter)
	userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
}
