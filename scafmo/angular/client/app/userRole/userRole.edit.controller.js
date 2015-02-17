'use strict';



angular.module('angularDemoApp')
    .controller('UserRoleEditController', function ($scope, $state, $q, $stateParams, UserRoleService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		UserRoleService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.userRole = angular.extend({}, $scope.userRole , response);
			       	$scope.orig = angular.copy($scope.userRole );
		       	}
	     	);
    	}else{
    		$scope.userRole = new UserRoleService();
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
	    		UserRoleService.update($scope.userRole, function(response) {	
	    			$translate('pages.UserRole.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			UserRoleService.save($scope.userRole,function(response) {
					
    				$translate('pages.UserRole.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
