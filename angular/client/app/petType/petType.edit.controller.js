'use strict';



angular.module('angularDemoApp')
    .controller('PetTypeEditController', function ($scope, $state, $q, $stateParams, PetTypeService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		PetTypeService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.petType = angular.extend({}, $scope.petType , response);
			       	$scope.orig = angular.copy($scope.petType );
		       	}
	     	);
    	}else{
    		$scope.petType = new PetTypeService();
    	}
		
	
	    $scope.submit = function(frmController) {
			var deferred = $q.defer();
	    	var errorCallback = function(response){
					if (response.data.errors) {
		                angular.forEach(response.data.errors, function (error) {
		                    frmController.setExternalValidation(error.field, undefined, error.message);
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
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
