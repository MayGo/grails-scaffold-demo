module.exports = function(){
	angular.module('httpBackendMock', ['ngMockE2E'])
		.run(function($httpBackend, appConfig) {
			console.log("running module roleHttpBackendMock")
			function quote(str) {
				return str.replace(/(?=[\/\\^$*+?.()|{}[\]])/g, "\\");
			}

			var url = appConfig.restUrl + '/roles/v1';
			$httpBackend.whenPOST(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {id : 1}];});//edit
			$httpBackend.whenPOST(new RegExp(quote(url))).respond(function(method, url){return [200, {'id' : 1}];});//create
			$httpBackend.whenGET(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {'id' : 1}];});//view
			$httpBackend.whenDELETE(new RegExp(quote(url + "/1"))).respond(function(method, url){return [204];});//delete
			$httpBackend.whenGET(new RegExp(quote(url) + ".*")).respond(function(method, url){return [200, [{'id' : 1}]];});//list
			//Mock relations

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
