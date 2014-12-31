// Place your Spring DSL code here
beans = {

		customMarshallerRegistrar(defpackage.CustomMarshallerRegistrar)
		
	 for (domainClass in application.domainClasses) {
	     "json${domainClass.shortName}CollectionRenderer"(defpackage.CustomJsonCollectionRenderer, domainClass.clazz)
	 }
		

}
