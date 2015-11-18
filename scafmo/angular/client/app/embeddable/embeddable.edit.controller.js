'use strict';



angular.module('angularDemoApp')
    .controller('EmbeddableEditController', function ($scope, $state, $q, $stateParams, EmbeddableService, embeddableData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.embeddable = embeddableData;


	    $scope.submit = function(frmController) {
			var deferred = $q.defer();
	    	var errorCallback = function(response){
					if (response.data.errors) {
		                angular.forEach(response.data.errors, function (error) {
							if(angular.element('#'+error.field).length) {
								frmController.setExternalValidation(error.field, undefined, error.message);
							} else {
								logger.error(error.message);
							}
		                });
		            }
					deferred.reject(response);
		       };

	    	if($scope.isEditForm){
	    		EmbeddableService.update($scope.embeddable, function(response) {	
	    			$translate('pages.embeddable.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			EmbeddableService.save($scope.embeddable,function(response) {
					
    				$translate('pages.embeddable.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
