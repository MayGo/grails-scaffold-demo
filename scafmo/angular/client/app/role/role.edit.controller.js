'use strict';



angular.module('angularDemoApp')
    .controller('RoleEditController', function ($scope, $state, $q, $stateParams, RoleService, roleData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.role = roleData;


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
	    		RoleService.update($scope.role, function(response) {	
	    			$translate('pages.role.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			RoleService.save($scope.role,function(response) {
					
    				$translate('pages.role.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
