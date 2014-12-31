'use strict';

describe('Service: DivisionCollectionlessService', function () {

  // load the service's module
  beforeEach(module('angularDemoApp'));

  // instantiate service
  var service;
  var httpBackend;
  var config;
  beforeEach(inject(function (_DivisionCollectionlessService_, $httpBackend, appConfig) {
    service = _DivisionCollectionlessService_;
    httpBackend = $httpBackend;
    config = appConfig;
  }));


  it('query() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenGET(config.restUrl + 'divisioncollectionlesss').respond([{"id":1},{"id":2}]);
  	
    service.query({}, function(response, responseHeaders){
    	var ids = _.pluck(response, 'id');
		expect(_.isEqual(ids, [1, 2])).toEqual(true);
	});
	
    httpBackend.flush();
  });
  
  
  it('get() should respond', function () {
  	httpBackend.whenGET('l10n/en.js').respond();
  	httpBackend.whenGET(config.restUrl + 'divisioncollectionlesss/1').respond({"id":1});
  	
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
  	httpBackend.whenPUT(config.restUrl + 'divisioncollectionlesss/1').respond({"id":1});
  	
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
  	httpBackend.whenPOST(config.restUrl + 'divisioncollectionlesss').respond({"id":1});
  	
  	service.save({some:'prop'}).$promise.then(
        function( response ){
	       	var id = response.id;
			expect(id).toEqual(1);
       	}
 	);
	
    httpBackend.flush();
  });
});
