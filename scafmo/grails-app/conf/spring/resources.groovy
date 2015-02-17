// Place your Spring DSL code here
beans = {

		
	def conf = grails.plugin.springsecurity.SpringSecurityUtils.securityConfig
	restBuilder(grails.plugins.rest.client.RestBuilder)
	tokenStorageService(ee.smit.security.CasTokenStorageService) {
		grailsCacheManager = ref('grailsCacheManager')
		cacheName = conf.rest.token.storage.grailsCacheName
		ldapService = ref('ldapService')
		restBuilder = ref('restBuilder')
	}
		


		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		
	for (domainClass in application.domainClasses) {
		"json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	}
		
		nonAuthFilter(defpackage.NonAuthenticationFilter)
		
		if(grails.util.Environment.current == grails.util.Environment.TEST && !grails.util.Holders.config.functionalTest.userName) {
			userDetailsService(org.springframework.security.provisioning.InMemoryUserDetailsManager, [])
		}


}
