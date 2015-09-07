package scafmo.embedded

import grails.compiler.GrailsCompileStatic
import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.SimpleMapDataBindingSource
import grails.transaction.Transactional
import defpackage.exceptions.ResourceNotFound

@GrailsCompileStatic
@Transactional
class EmbedModifyService {
	GrailsWebDataBinder grailsWebDataBinder

	Embed createEmbed(Map data) {
		Embed embed = Embed.newInstance()
		return createOrUpdate(embed, data)
	}

	Embed updateEmbed(Map data) {
		Long objId = (Long)data.id
		if (!objId || objId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}

		Embed embed = Embed.where { id == objId }.find()

		if (!embed) {
			throw new ResourceNotFound("No Embed found with Id :[$objId]")
		}

		return createOrUpdate(embed, data)
	}

	Embed createOrUpdate(Embed embed, Map data) {
		if (!data) {
			throw new IllegalArgumentException('no.data')
		}

		grailsWebDataBinder.bind embed, data as SimpleMapDataBindingSource

		embed.save failOnError: true

		return embed
	}

	void deleteEmbed(Long embedId) {
		if (!embedId || embedId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Embed embed = Embed.where { id == embedId }.find()

		if (!embed) {
			throw new ResourceNotFound("No Embed found with Id:$embedId")
		}

		embed.delete()
	}
}

