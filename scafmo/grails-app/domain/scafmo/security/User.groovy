package scafmo.security

class User {


	String username
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired


	static constraints = {
		username blank: false, unique: true
	}

	static mapping = {
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}


	
}
