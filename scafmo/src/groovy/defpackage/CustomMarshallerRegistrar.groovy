package defpackage

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.example.pomodoro.Tag
import org.example.pomodoro.Task
import org.grails.samples.Owner
import org.grails.samples.Person
import org.grails.samples.Pet
import org.grails.samples.PetType
import org.grails.samples.Speciality
import org.grails.samples.Vet
import org.grails.samples.Visit
import scafmo.collection.DivisionCollection
import scafmo.collection.DivisionCollectionless
import scafmo.collection.PersonCollection
import scafmo.collection.PersonCollectionless
import scafmo.constr.TestNumber
import scafmo.constr.TestOther
import scafmo.constr.TestString
import scafmo.security.Role
import scafmo.security.User
import scafmo.security.UserRole
import test.Classifier

class CustomMarshallerRegistrar {

	static Map domainPropertiesCache = [:]

	static List getDomainProperties(Class domainClass) {
		String name = domainClass.name

		if (domainPropertiesCache[name]) {
			return domainPropertiesCache[name]
		}

		def grailsDomainClass = new DefaultGrailsDomainClass(domainClass)

		domainPropertiesCache[name] = grailsDomainClass.persistentProperties*.name

		return domainPropertiesCache[name]
	}

	static Map createMap(List excludesList = []) {
		Map excludes = { [:].withDefault { owner.call() } } ()

		for (item in excludesList) {
			List parts = item?.tokenize('.')

			if (parts?.size() > 1 && parts.first() in excludesList) {
				continue
			}

			Map m = excludes
			parts.each {
				m = m[it.toString()]
			}
		}
		return excludes
	}

	static Map filter(domain, Map excludes) {
		if (!domain) {
			return [:]
		}

		Map res = [:]

		if (!excludes.containsKey('id')) {
			res << [id: domain.id]
		}
		if (!excludes.containsKey('version')) {
			res << [version: domain.version]
		}
		for (String key in getDomainProperties(domain.class)) {
			def value = domain[key]
			if (excludes.containsKey(key) && !excludes[key]) {
				continue
			}
			if (excludes.containsKey(key) && excludes[key] && value) {
				boolean isCollection = (value instanceof SortedMap
						|| value instanceof SortedSet
						|| value instanceof Set
						|| value instanceof Map
						|| value instanceof Collection)
				if (isCollection) {
					List objList = []
					for (d2 in value) {
						objList << filter(d2, excludes[key])
					}
					res[key] = objList
				} else {
					res[key] = filter(value, excludes[key])
				}
			} else if (value != null && value != '') {
				if (value.class?.isEnum()) {
					res[key] = value.name()
				} else {
					res[key] = value
				}
			}
		}
		return res
	}

	@javax.annotation.PostConstruct
    static void registerMarshallers() {
		int priority = 10
		
		JSON.registerObjectMarshaller Tag, priority, { Tag instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'tasks']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Task, priority, { Task instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'tags']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Owner, priority, { Owner instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'pets']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Person, priority, { Person instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Pet, priority, { Pet instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'ownerId',
'owner.lastName',
'owner.pets',
'owner.telephone',
'typeId',
'visits']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller PetType, priority, { PetType instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Speciality, priority, { Speciality instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Vet, priority, { Vet instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'specialities']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Visit, priority, { Visit instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'petId',
'pet.owner',
'pet.ownerId',
'pet.type',
'pet.typeId',
'pet.visits']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller DivisionCollection, priority, { DivisionCollection instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'headDivisionId',
'headDivision.headDivision',
'headDivision.headDivisionId',
'headDivision.persons',
'persons']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller DivisionCollectionless, priority, { DivisionCollectionless instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'headDivisionId',
'headDivision.headDivision',
'headDivision.headDivisionId']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller PersonCollection, priority, { PersonCollection instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'divisionId',
'division.headDivision',
'division.headDivisionId',
'division.persons']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller PersonCollectionless, priority, { PersonCollectionless instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'divisionId',
'division.headDivision',
'division.headDivisionId']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller TestNumber, priority, { TestNumber instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller TestOther, priority, { TestOther instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'testStringTypeId',
'testStringType.inListStr',
'testStringType.matchesStr',
'testStringType.maxSizeStr',
'testStringType.minSizeStr',
'testStringType.notEqualStr',
'testStringType.sizeStr',
'testStringType.uniqueStr',
'testStringType.urlStr']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller TestString, priority, { TestString instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Role, priority, { Role instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller User, priority, { User instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller UserRole, priority, { UserRole instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = [
'roleId',
'userId',
'user.passwordExpired',
'user.username']
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
		JSON.registerObjectMarshaller Classifier, priority, { Classifier instance, JSON json ->
			Class cl = instance.getClass()
			List defaultExcludes = []
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
    }
}
