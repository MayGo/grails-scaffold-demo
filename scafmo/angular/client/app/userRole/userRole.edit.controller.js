'use strict';



angular.module('angularDemoApp')
    .controller('UserRoleEditController', function ($scope, $state, $q, $stateParams, UserRoleService, userRoleData, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.userRole = userRoleData;


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
	    		UserRoleService.update($scope.userRole, function(response) {	
	    			$translate('pages.userRole.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			UserRoleService.save($scope.userRole,function(response) {
					
    				$translate('pages.userRole.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
