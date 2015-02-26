package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class UserModifyService {
	def grailsWebDataBinder

	User createUser(Map data){
		User user = User.newInstance()
		return createOrUpdate(user, data)
	}

	User updateUser(Map data){
		if(!data.id || data.id < 0){
			throw new IllegalArgumentException("There is no valid 'id'")
		}
		User user = queryForUser(data.id)

		if(!user){
			throw new ResourceNotFound("No User found with Id :[${data.id}]")
		}

		return createOrUpdate(user, data)
	}

	User createOrUpdate(User user, Map data){
		if(!data){
			throw new IllegalArgumentException("Data map is empty.")
		}

		grailsWebDataBinder.bind user, data as SimpleMapDataBindingSource

		user.save flush: true, failOnError: true

		return user
	}

	User queryForUser(long id){
		return User.get(id)
	}
}
