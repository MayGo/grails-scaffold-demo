'use strict';



angular.module('angularDemoApp')
    .controller('SpecialityEditController', function ($scope, $state, $q, $stateParams, SpecialityService, specialityData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.speciality = specialityData;


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
	    		SpecialityService.update($scope.speciality, function(response) {	
	    			$translate('pages.speciality.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			SpecialityService.save($scope.speciality,function(response) {
					
    				$translate('pages.speciality.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
