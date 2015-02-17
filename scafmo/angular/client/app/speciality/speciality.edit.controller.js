'use strict';



angular.module('angularDemoApp')
    .controller('SpecialityEditController', function ($scope, $state, $q, $stateParams, SpecialityService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		SpecialityService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.speciality = angular.extend({}, $scope.speciality , response);
			       	$scope.orig = angular.copy($scope.speciality );
		       	}
	     	);
    	}else{
    		$scope.speciality = new SpecialityService();
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
	    		SpecialityService.update($scope.speciality, function(response) {	
	    			$translate('pages.Speciality.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			SpecialityService.save($scope.speciality,function(response) {
					
    				$translate('pages.Speciality.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
	});
