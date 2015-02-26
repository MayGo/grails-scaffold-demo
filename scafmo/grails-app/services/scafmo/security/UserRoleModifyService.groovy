package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class UserRoleModifyService {
	def grailsWebDataBinder

	UserRole createUserRole(Map data){
		UserRole userRole = UserRole.newInstance()
		return createOrUpdate(userRole, data)
	}

	UserRole updateUserRole(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		UserRole userRole = queryForUserRole(data.id)

		if(!userRole){
			throw new ResourceNotFound("No UserRole found with Id :[${data.id}]")
		}

		return createOrUpdate(userRole, data)
	}

	UserRole createOrUpdate(UserRole userRole, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind userRole, data as SimpleMapDataBindingSource

		userRole.save flush: true, failOnError: true

		return userRole
	}

	UserRole queryForUserRole(long id){
		return UserRole.get(id)
	}
}
