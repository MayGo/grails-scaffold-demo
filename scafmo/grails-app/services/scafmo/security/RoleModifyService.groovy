package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class RoleModifyService {
	def grailsWebDataBinder

	Role createRole(Map data){
		Role role = Role.newInstance()
		return createOrUpdate(role, data)
	}

	Role updateRole(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		Role role = queryForRole(data.id)

		if(!role){
			throw new ResourceNotFound("No Role found with Id :[${data.id}]")
		}

		return createOrUpdate(role, data)
	}

	Role createOrUpdate(Role role, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind role, data as SimpleMapDataBindingSource

		role.save flush: true, failOnError: true

		return role
	}

	Role queryForRole(long id){
		return Role.get(id)
	}
}
