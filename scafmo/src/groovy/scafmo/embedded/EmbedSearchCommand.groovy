package scafmo.embedded

import grails.validation.Validateable
import groovy.transform.ToString

@Validateable
@ToString
class EmbedSearchCommand {

	Long id
	String searchString
	List<types.CustomMap> acCustomMaps
	types.CustomMap acCustomMap
	List<Map> acMaps
	Map acMap
	List<String> acStrs
	String acStr
	String muFileLocation
	String myFile
	Map myEmbeddedFieldJsonMap
	List<Map> myEmbeddedFieldMyAcs
	Map myEmbeddedFieldMyAc
	String myEmbeddedFieldStr

	static constraints = {
		id nullable: true
		searchString nullable: true
		acCustomMaps nullable: true
		acCustomMap nullable: true
		acMaps nullable: true
		acMap nullable: true
		acStrs nullable: true
		acStr nullable: true
		muFileLocation nullable: true
		myFile nullable: true
		myEmbeddedFieldJsonMap nullable: true
		myEmbeddedFieldMyAcs nullable: true
		myEmbeddedFieldMyAc nullable: true
		myEmbeddedFieldStr nullable: true

	}
}
