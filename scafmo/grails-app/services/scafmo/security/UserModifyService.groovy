package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class UserModifyService {
	def grailsWebDataBinder

	User createUser(Map data) {
		User user = User.newInstance()
		return createOrUpdate(user, data)
	}

	User updateUser(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		User user = User.where { id == data.id }.find()

		if (!user) {
			throw new ResourceNotFound("No User found with Id :[${data.id}]")
		}

		return createOrUpdate(user, data)
	}

	User createOrUpdate(User user, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind user, data as SimpleMapDataBindingSource

		user.save failOnError: true

		return user
	}

	void deleteUser(Long userId) {
		if (!userId || userId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		User user = User.where { id == userId }.find()

		if (!user) {
			throw new ResourceNotFound("No User found with Id:$userId")
		}

		user.delete()
	}
}

