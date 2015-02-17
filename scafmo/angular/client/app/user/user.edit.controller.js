'use strict';



angular.module('angularDemoApp')
    .controller('UserEditController', function ($scope, $state, $q, $stateParams, UserService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		UserService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.user = angular.extend({}, $scope.user , response);
			       	$scope.orig = angular.copy($scope.user );
		       	}
	     	);
    	}else{
    		$scope.user = new UserService();
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
	    		UserService.update($scope.user, function(response) {	
	    			$translate('pages.User.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			UserService.save($scope.user,function(response) {
					
    				$translate('pages.User.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
	});
