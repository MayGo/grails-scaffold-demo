'use strict';



angular.module('angularDemoApp')
    .controller('RoleEditController', function ($scope, $state, $q, $stateParams, RoleService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		RoleService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.role = angular.extend({}, $scope.role , response);
			       	$scope.orig = angular.copy($scope.role );
		       	}
	     	);
    	}else{
    		$scope.role = new RoleService();
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
	    		RoleService.update($scope.role, function(response) {	
	    			$translate('pages.Role.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			RoleService.save($scope.role,function(response) {
					
    				$translate('pages.Role.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
