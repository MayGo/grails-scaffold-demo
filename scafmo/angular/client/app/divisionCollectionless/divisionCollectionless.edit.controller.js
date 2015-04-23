'use strict';



angular.module('angularDemoApp')
    .controller('DivisionCollectionlessEditController', function ($scope, $state, $q, $stateParams, DivisionCollectionlessService, divisionCollectionlessData, $translate, inform ) {
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
								inform.add(error.message, {ttl: -1,'type': 'warning'});
							}
		                });
		            }
					deferred.reject(response);
		       };

	    	if($scope.isEditForm){
	    		DivisionCollectionlessService.update($scope.divisionCollectionless, function(response) {	
	    			$translate('pages.DivisionCollectionless.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			DivisionCollectionlessService.save($scope.divisionCollectionless,function(response) {
					
    				$translate('pages.DivisionCollectionless.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
