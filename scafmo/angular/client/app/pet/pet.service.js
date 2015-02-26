'use strict';

angular.module('angularDemoApp')
  .service('PetService', function($resource, $translate, appConfig, inform){
  		var service = {};
  		
  		var resource = $resource(appConfig.restUrl + '/pets/v1/:id', { id: '@id' }, {
            //query: {method:'GET',  params:{}, isArray:true},
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        });
		service = resource;
		service.deleteInstance = function(instance) {
			return instance.$delete(
				function(instance) {
					$translate('pages.Pet.messages.delete').then(function (msg) {
						inform.add(msg, {'type': 'warning'});
					});

					return instance;//returning chained promise
				},
				function (httpError) {
					console.error("Could not delete instance.");
					console.error( httpError.status + " : " +  httpError.data );
					return httpError;
				}
			);
		};
        return service;
    });