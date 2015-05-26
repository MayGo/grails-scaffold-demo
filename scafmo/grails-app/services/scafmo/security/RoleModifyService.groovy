package scafmo.security

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class RoleModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Role createRole(Map data) {
		Role role = Role.newInstance()
		return createOrUpdate(role, data)
	}

	Role updateRole(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Role role = Role.where { id == objId }.find()

		if (!role) {
			throw new ResourceNotFound("No Role found with Id :[$objId]")
		}

		return createOrUpdate(role, data)
	}

	Role createOrUpdate(Role role, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind role, data as SimpleMapDataBindingSource

		role.save failOnError: true

		return role
	}

	void deleteRole(Long roleId) {
		if (!roleId || roleId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Role role = Role.where { id == roleId }.find()

		if (!role) {
			throw new ResourceNotFound("No Role found with Id:$roleId")
		}

		role.delete()
	}
}

