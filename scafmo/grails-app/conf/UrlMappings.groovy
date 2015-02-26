class UrlMappings {

	static mappings = {

		'/tags/v1'(resources: 'Tag', namespace: 'v1')
		'/tasks/v1'(resources: 'Task', namespace: 'v1')
		'/owners/v1'(resources: 'Owner', namespace: 'v1')
		'/persons/v1'(resources: 'Person', namespace: 'v1')
		'/pets/v1'(resources: 'Pet', namespace: 'v1')
		'/pettypes/v1'(resources: 'PetType', namespace: 'v1')
		'/specialitys/v1'(resources: 'Speciality', namespace: 'v1')
		'/vets/v1'(resources: 'Vet', namespace: 'v1')
		'/visits/v1'(resources: 'Visit', namespace: 'v1')
		'/divisioncollections/v1'(resources: 'DivisionCollection', namespace: 'v1')
		'/divisioncollectionlesss/v1'(resources: 'DivisionCollectionless', namespace: 'v1')
		'/personcollections/v1'(resources: 'PersonCollection', namespace: 'v1')
		'/personcollectionlesss/v1'(resources: 'PersonCollectionless', namespace: 'v1')
		'/testnumbers/v1'(resources: 'TestNumber', namespace: 'v1')
		'/testothers/v1'(resources: 'TestOther', namespace: 'v1')
		'/teststrings/v1'(resources: 'TestString', namespace: 'v1')
		'/roles/v1'(resources: 'Role', namespace: 'v1')
		'/users/v1'(resources: 'User', namespace: 'v1')
		'/userroles/v1'(resources: 'UserRole', namespace: 'v1')
		'/classifiers/v1'(resources: 'Classifier', namespace: 'v1')
		'/'(redirect:'/ng/index.html')
		
		"/ng/$asset**" {
			controller = 'dirserve'
			action = 'index'
			dirserveBase = 'angular/client'
		}
		

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        
        
	}
}
