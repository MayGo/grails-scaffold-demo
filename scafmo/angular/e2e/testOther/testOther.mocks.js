module.exports = function(){
	angular.module('httpBackendMock', ['ngMockE2E'])
		.run(function($httpBackend, appConfig) {
			console.log("running module testOtherHttpBackendMock")
			function quote(str) {
				return str.replace(/(?=[\/\\^$*+?.()|{}[\]])/g, "\\");
			}

			var url = appConfig.restUrl + '/testothers/v1';
			$httpBackend.whenPOST(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {id : 1}];});//edit
			$httpBackend.whenPOST(new RegExp(quote(url))).respond(function(method, url){return [200, {'id' : 1}];});//create
			$httpBackend.whenGET(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {'id' : 1}];});//view
			$httpBackend.whenDELETE(new RegExp(quote(url + "/1"))).respond(function(method, url){return [204];});//delete
			$httpBackend.whenGET(new RegExp(quote(url) + ".*")).respond(function(method, url){return [200, [{'id' : 1}]];});//list
			//Mock relations

			var testStringTypeUrl =/.*\/teststrings\/v1.*/;
			$httpBackend.whenGET(testStringTypeUrl).respond(function(method, url){return [200, [{'id': '1','urlStr': 'http://www.test760.com','uniqueStr': 'U 759','sizeStr': 'sizeStr'}]]});//list

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
