'use strict';



angular.module('angularDemoApp')
    .controller('DivisionCollectionlessEditController', function ($scope, $state, $q, $stateParams, DivisionCollectionlessService, divisionCollectionlessData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.divisionCollectionless = divisionCollectionlessData;


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
	    		DivisionCollectionlessService.update($scope.divisionCollectionless, function(response) {	
	    			$translate('pages.divisionCollectionless.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			DivisionCollectionlessService.save($scope.divisionCollectionless,function(response) {
					
    				$translate('pages.divisionCollectionless.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
