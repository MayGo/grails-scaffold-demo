'use strict';

angular.module('angularDemoApp')
  .service('TagService', function($resource, $translate, appConfig, logger){
  		var service = {};
  		
  		var resource = $resource(appConfig.restUrl + '/tags/v1/:id', { id: '@id' }, {
            //query: {method:'GET',  params:{}, isArray:true},
            update: {
                method: 'PUT' // this method issues a PUT request
            }
        });
		service = resource;
		service.deleteInstance = function(instance) {
			return instance.$delete(
				function(instance) {
					$translate('pages.tag.messages.delete').then(function (msg) {
						logger.info(msg);
					});

					return instance;//returning chained promise
				},
				function (httpError) {
					console.error('Could not delete instance.');
					console.error( httpError.status + ' : ' +  httpError.data );
					return httpError;
				}
			);
		};
        return service;
    });