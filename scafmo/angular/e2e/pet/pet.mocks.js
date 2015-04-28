module.exports = function(){
	angular.module('httpBackendMock', ['ngMockE2E'])
		.run(function($httpBackend, appConfig) {
			console.log("running module petHttpBackendMock")
			function quote(str) {
				return str.replace(/(?=[\/\\^$*+?.()|{}[\]])/g, "\\");
			}

			var url = appConfig.restUrl + '/pets/v1';
			$httpBackend.whenPOST(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {id : 1}];});//edit
			$httpBackend.whenPOST(new RegExp(quote(url))).respond(function(method, url){return [200, {'id' : 1}];});//create
			$httpBackend.whenGET(new RegExp(quote(url + "/1"))).respond(function(method, url){return [200, {'id' : 1}];});//view
			$httpBackend.whenDELETE(new RegExp(quote(url + "/1"))).respond(function(method, url){return [204];});//delete
			$httpBackend.whenGET(new RegExp(quote(url) + ".*")).respond(function(method, url){return [200, [{'id' : 1}]];});//list
			//Mock relations

			var visitsUrl =/.*\/visits\/v1.*/;
			$httpBackend.whenGET(visitsUrl).respond(function(method, url){return [200, [{'id': '1','description': 'description','date': 'Tue Apr 28 00:00:00 EEST 2015'}]]});//list

			var typeUrl =/.*\/pettypes\/v1.*/;
			$httpBackend.whenGET(typeUrl).respond(function(method, url){return [200, [{'id': '1','name': 'Type 455'}]]});//list

			var ownerUrl =/.*\/owners\/v1.*/;
			$httpBackend.whenGET(ownerUrl).respond(function(method, url){return [200, [{'id': '1','telephone': '555452','lastName': 'lastName','firstName': 'firstName'}]]});//list

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
