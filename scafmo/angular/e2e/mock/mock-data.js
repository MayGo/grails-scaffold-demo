module.exports = function(){
	angular.module('httpBackendMock', ['ngMockE2E'])
    	.run(function($httpBackend, appConfig) {


	     	var tagUrl = appConfig.restUrl + '/tags/v1';
	        $httpBackend.whenPOST(tagUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(tagUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(tagUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var taskUrl = appConfig.restUrl + '/tasks/v1';
	        $httpBackend.whenPOST(taskUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(taskUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(taskUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var ownerUrl = appConfig.restUrl + '/owners/v1';
	        $httpBackend.whenPOST(ownerUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(ownerUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(ownerUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var personUrl = appConfig.restUrl + '/persons/v1';
	        $httpBackend.whenPOST(personUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(personUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(personUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var petUrl = appConfig.restUrl + '/pets/v1';
	        $httpBackend.whenPOST(petUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(petUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(petUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var petTypeUrl = appConfig.restUrl + '/pettypes/v1';
	        $httpBackend.whenPOST(petTypeUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(petTypeUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(petTypeUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var specialityUrl = appConfig.restUrl + '/specialitys/v1';
	        $httpBackend.whenPOST(specialityUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(specialityUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(specialityUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var vetUrl = appConfig.restUrl + '/vets/v1';
	        $httpBackend.whenPOST(vetUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(vetUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(vetUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var visitUrl = appConfig.restUrl + '/visits/v1';
	        $httpBackend.whenPOST(visitUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(visitUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(visitUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var divisionCollectionUrl = appConfig.restUrl + '/divisioncollections/v1';
	        $httpBackend.whenPOST(divisionCollectionUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(divisionCollectionUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(divisionCollectionUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var divisionCollectionlessUrl = appConfig.restUrl + '/divisioncollectionlesss/v1';
	        $httpBackend.whenPOST(divisionCollectionlessUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(divisionCollectionlessUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(divisionCollectionlessUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var personCollectionUrl = appConfig.restUrl + '/personcollections/v1';
	        $httpBackend.whenPOST(personCollectionUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(personCollectionUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(personCollectionUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var personCollectionlessUrl = appConfig.restUrl + '/personcollectionlesss/v1';
	        $httpBackend.whenPOST(personCollectionlessUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(personCollectionlessUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(personCollectionlessUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var testNumberUrl = appConfig.restUrl + '/testnumbers/v1';
	        $httpBackend.whenPOST(testNumberUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(testNumberUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(testNumberUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var testOtherUrl = appConfig.restUrl + '/testothers/v1';
	        $httpBackend.whenPOST(testOtherUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(testOtherUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(testOtherUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var testStringUrl = appConfig.restUrl + '/teststrings/v1';
	        $httpBackend.whenPOST(testStringUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(testStringUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(testStringUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var roleUrl = appConfig.restUrl + '/roles/v1';
	        $httpBackend.whenPOST(roleUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(roleUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(roleUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var userUrl = appConfig.restUrl + '/users/v1';
	        $httpBackend.whenPOST(userUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(userUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(userUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var userRoleUrl = appConfig.restUrl + '/userroles/v1';
	        $httpBackend.whenPOST(userRoleUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(userRoleUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(userRoleUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

	     	var classifierUrl = appConfig.restUrl + '/classifiers/v1';
	        $httpBackend.whenPOST(classifierUrl).respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenPOST(classifierUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});
	        $httpBackend.whenGET(classifierUrl + "/1").respond(function(method, url){return [200, {'id' : 1}];});

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

