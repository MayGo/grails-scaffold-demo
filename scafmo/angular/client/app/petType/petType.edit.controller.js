'use strict';



angular.module('angularDemoApp')
    .controller('PetTypeEditController', function ($scope, $state, $q, $stateParams, PetTypeService, petTypeData, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.petType = petTypeData;


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
	    		PetTypeService.update($scope.petType, function(response) {	
	    			$translate('pages.PetType.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PetTypeService.save($scope.petType,function(response) {
					
    				$translate('pages.PetType.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
