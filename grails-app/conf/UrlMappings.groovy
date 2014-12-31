class UrlMappings {

	static mappings = {

		'/divisioncollections'(resources:'DivisionCollection')
		'/divisioncollectionlesss'(resources:'DivisionCollectionless')
		'/personcollections'(resources:'PersonCollection')
		'/personcollectionlesss'(resources:'PersonCollectionless')
		'/testnumbers'(resources:'TestNumber')
		'/testothers'(resources:'TestOther')
		'/teststrings'(resources:'TestString')
		'/roles'(resources:'Role')
		'/users'(resources:'User')
		'/userroles'(resources:'UserRole')
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
