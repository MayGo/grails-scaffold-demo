package defpackage

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class CustomMarshallerRegistrar {
	
	static Map domainPropertiesCache = [:]
	
	static List getDomainProperties(Class domainClass){
		String name = domainClass.name
		if(domainPropertiesCache[name]) return domainPropertiesCache[name]
		def grailsDomainClass = new DefaultGrailsDomainClass(domainClass)
		domainPropertiesCache[name] = grailsDomainClass.getPersistentProperties()*.name
		return domainPropertiesCache[name]
	}
	
	static Map createMap(List excludesList = []){
		Map excludes = { [:].withDefault{ owner.call() } }()
		for(item in excludesList){
			List parts = item?.tokenize(".")
			if(parts?.size()>1 && parts.first() in excludesList) continue
			Map m = excludes
			parts.each{
				m = m["$it"]
			}
		}
		return excludes
	}
	
	
	
	static Map filter( domain, Map excludes){
		if(!domain) return [:]
		Map res = [:]
		if(!excludes.containsKey("id"))res << [id: domain.id]
		for(key in getDomainProperties(domain.class)){
			def value = domain[key]
			if(excludes.containsKey(key) && !excludes["${key}"]) continue
			if(excludes.containsKey(key) && excludes["${key}"] && value){
				boolean isCollection = (value instanceof SortedMap
					|| value instanceof SortedSet
					|| value instanceof Set
					|| value instanceof Map
					|| value instanceof Collection)
				if(isCollection){
					List objList = []
					for(d2 in value){
						objList << filter(d2, excludes["${key}"])
					}
					res << ["${key}":objList]
				}else{
					res << ["${key}":filter(value, excludes["${key}"])]
				}
			}else if(value != null && value != ""){
				if(value.class.isEnum()){
					res << ["${key}": value.name()]
				}else{
					res << ["${key}": value]
				}
			}
		}
		return res
	}
	
    @javax.annotation.PostConstruct
    static void registerMarshallers() {             
		
		JSON.registerObjectMarshaller scafmo.collection.DivisionCollection, {scafmo.collection.DivisionCollection instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['headDivisionId', 'headDivision.headDivision', 'headDivision.headDivisionId', 'headDivision.persons', 'persons'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.collection.DivisionCollectionless, {scafmo.collection.DivisionCollectionless instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['headDivisionId', 'headDivision.headDivision', 'headDivision.headDivisionId'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.collection.PersonCollection, {scafmo.collection.PersonCollection instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['divisionId', 'division.headDivision', 'division.headDivisionId', 'division.persons'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.collection.PersonCollectionless, {scafmo.collection.PersonCollectionless instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['divisionId', 'division.headDivision', 'division.headDivisionId'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.constr.TestNumber, {scafmo.constr.TestNumber instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = [] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.constr.TestOther, {scafmo.constr.TestOther instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['testStringTypeId', 'testStringType.blankStr', 'testStringType.creditCardStr', 'testStringType.emailStr', 'testStringType.inListStr', 'testStringType.matchesStr', 'testStringType.maxSizeStr', 'testStringType.minSizeStr', 'testStringType.notEqualStr', 'testStringType.sizeStr', 'testStringType.uniqueStr', 'testStringType.urlStr'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.constr.TestString, {scafmo.constr.TestString instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = [] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.security.Role, {scafmo.security.Role instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = [] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.security.User, {scafmo.security.User instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = [] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
		JSON.registerObjectMarshaller scafmo.security.UserRole, {scafmo.security.UserRole instance, JSON json->
			Class cl = instance.getClass()
			List defaultExcludes = ['roleId', 'userId', 'user.accountExpired', 'user.accountLocked', 'user.enabled', 'user.passwordExpired'] 
			Map excludes = createMap(defaultExcludes + json.getExcludes(cl) - json.getIncludes(cl))
			return filter(instance, excludes)
		}
	
    }
}