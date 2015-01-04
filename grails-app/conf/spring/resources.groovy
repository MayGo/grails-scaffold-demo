import grails.util.Environment

beans = {

		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		
	 for (domainClass in application.domainClasses) {
	     "json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	 }
	 if(Environment.current == Environment.TEST) {
       
    }
		//authenticationManager(defpackage.MockAuthenticationManager)
		/* def conf = grails.plugin.springsecurity.SpringSecurityUtils.securityConfig
		 restAuthenticationFilter(defpackage.MockRestAuthenticationFilter) {
                authenticationManager = ref('authenticationManager')
                authenticationSuccessHandler = ref('restAuthenticationSuccessHandler')
                authenticationFailureHandler = ref('restAuthenticationFailureHandler')
                authenticationDetailsSource = ref('authenticationDetailsSource')
                credentialsExtractor = ref('credentialsExtractor')
                endpointUrl = ''
                tokenGenerator = ref('tokenGenerator')
                tokenStorageService = ref('tokenStorageService')
            }*/
			nonAuthFilter(defpackage.NonAuthenticationFilter)
			 userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
			// passwordEncoder(org.springframework.security.authentication.encoding.PlaintextPasswordEncoder)

}
