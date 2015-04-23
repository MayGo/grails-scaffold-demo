package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional
class UserRoleModifyService {
	def grailsWebDataBinder

	UserRole createUserRole(Map data) {
		UserRole userRole = UserRole.newInstance()
		return createOrUpdate(userRole, data)
	}

	UserRole updateUserRole(Map data) {
		if (!data.id || data.id < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		UserRole userRole = UserRole.where { id == data.id }.find()

		if (!userRole) {
			throw new ResourceNotFound("No UserRole found with Id :[${data.id}]")
		}

		return createOrUpdate(userRole, data)
	}

	UserRole createOrUpdate(UserRole userRole, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind userRole, data as SimpleMapDataBindingSource

		userRole.save failOnError: true

		return userRole
	}

	void deleteUserRole(Long userRoleId) {
		if (!userRoleId || userRoleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		UserRole userRole = UserRole.where { id == userRoleId }.find()

		if (!userRole) {
			throw new ResourceNotFound("No UserRole found with Id:$userRoleId")
		}

		userRole.delete()
	}
}

