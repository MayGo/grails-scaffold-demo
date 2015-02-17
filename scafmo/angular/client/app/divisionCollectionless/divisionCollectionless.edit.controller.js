'use strict';



angular.module('angularDemoApp')
    .controller('DivisionCollectionlessEditController', function ($scope, $state, $q, $stateParams, DivisionCollectionlessService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		DivisionCollectionlessService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.divisionCollectionless = angular.extend({}, $scope.divisionCollectionless , response);
			       	$scope.orig = angular.copy($scope.divisionCollectionless );
		       	}
	     	);
    	}else{
    		$scope.divisionCollectionless = new DivisionCollectionlessService();
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
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
