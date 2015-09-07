module.exports = function(){
	angular.module('httpBackendMock', ['ngMockE2E'])
		.run(function($httpBackend, appConfig) {
			console.log("running module embedHttpBackendMock")
			function quote(str) {
				return str.replace(/(?=[\/\\^$*+?.()|{}[\]])/g, "\\");
			}

			var url = appConfig.restUrl + '/embeds/v1';
			$httpBackend.whenPOST(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {id : 1}];});//edit
			$httpBackend.whenPOST(new RegExp(quote(url))).respond(function(method, url){return [200, {'id' : 1}];});//create
			$httpBackend.whenGET(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {'id' : 1}];});//view
			$httpBackend.whenDELETE(new RegExp(quote(url + "/1"))).respond(function(method, url){return [204];});//delete
			$httpBackend.whenGET(new RegExp(quote(url) + ".*")).respond(function(method, url){return [200, [{'id' : 1}]];});//list
			//Mock relations

			var acCustomMapUrl = appConfig['testStringUrl'] || 'http://localhost:8080/testStringUrl';
			var acCustomMapRe = new RegExp(quote(acCustomMapUrl) + ".*");
			$httpBackend.whenGET(acCustomMapRe).respond(function(method, url){return [200, [{"id":null,"version":null,"acCustomMap":{"mykey":"myvalue"},"acMap":{"mykey":"myvalue"},"acStr":"Blank 2","muFileLocation":"myfile.txt","myFile":[116,101,115,116,49]}]]});//list
			

			var acMapUrl = appConfig['testStringUrl'] || 'http://localhost:8080/testStringUrl';
			var acMapRe = new RegExp(quote(acMapUrl) + ".*");
			$httpBackend.whenGET(acMapRe).respond(function(method, url){return [200, [{"id":null,"version":null,"acCustomMap":{"mykey":"myvalue"},"acMap":{"mykey":"myvalue"},"acStr":"Blank 2","muFileLocation":"myfile.txt","myFile":[116,101,115,116,49]}]]});//list
			

			var acStrUrl = appConfig['testStringUrl'] || 'http://localhost:8080/testStringUrl';
			var acStrRe = new RegExp(quote(acStrUrl) + ".*");
			$httpBackend.whenGET(acStrRe).respond(function(method, url){return [200, [{"id":null,"version":null,"acCustomMap":{"mykey":"myvalue"},"acMap":{"mykey":"myvalue"},"acStr":"Blank 2","muFileLocation":"myfile.txt","myFile":[116,101,115,116,49]}]]});//list
			

			var myEmbeddedField_myAcUrl = appConfig['testStringUrl'] || 'http://localhost:8080/testStringUrl';
			var myEmbeddedField_myAcRe = new RegExp(quote(myEmbeddedField_myAcUrl) + ".*");
			$httpBackend.whenGET(myEmbeddedField_myAcRe).respond(function(method, url){return [200, [{"id":null,"version":null,"myAc":{"mykey":"myvalue"},"str":"Blank 5"}]]});//list
			

			//For everything else, don't mock
			$httpBackend.whenGET(/.*/).passThrough();
			$httpBackend.whenPOST(/.*/).passThrough();
			$httpBackend.whenHEAD(/.*/).passThrough();
			$httpBackend.whenDELETE(/.*/).passThrough();
			$httpBackend.whenPUT(/.*/).passThrough();
			$httpBackend.whenPATCH(/.*/).passThrough();
			$httpBackend.whenJSONP(/.*/).passThrough();

			
	});
}
