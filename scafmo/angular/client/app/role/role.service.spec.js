'use strict';

describe('Service: RoleService', function () {

  // load the service's module
  beforeEach(module('angularDemoApp'));

  // instantiate service
  var service;
  var httpBackend;
  var config;
  beforeEach(inject(function (_RoleService_, $httpBackend, appConfig) {
    service = _RoleService_;
    httpBackend = $httpBackend;
    config = appConfig;
  }));


  it('query() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenGET(config.restUrl + '/roles').respond([{"id":1},{"id":2}]);
  	
    service.query({}, function(response, responseHeaders){
    	var ids = _.pluck(response, 'id');
		expect(_.isEqual(ids, [1, 2])).toEqual(true);
	});
	
    httpBackend.flush();
  });
  
  
  it('get() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenGET(config.restUrl + '/roles/1').respond({"id":1});
  	
  	service.get({id:1}).$promise.then(
        function( response ){
	       	var id = response.id;
			expect(id).toEqual(1);
       	}
 	);
	
    httpBackend.flush();
  });
  
  
  it('update() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenPUT(config.restUrl + '/roles/1').respond({"id":1});
  	
  	service.update({id:1}).$promise.then(
        function( response ){
	       	var id = response.id;
			expect(id).toEqual(1);
       	}
 	);
	
    httpBackend.flush();
  });
  
  it('save() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenPOST(config.restUrl + '/roles').respond({"id":1});
  	
  	service.save({some:'prop'}).$promise.then(
        function( response ){
	       	var id = response.id;
			expect(id).toEqual(1);
       	}
 	);
	
    httpBackend.flush();
  });
});
