'use strict';



angular.module('angularDemoApp')
    .controller('TestNumberEditController', function ($scope, $state, $q, $stateParams, TestNumberService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		TestNumberService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.testNumber = angular.extend({}, $scope.testNumber , response);
			       	$scope.orig = angular.copy($scope.testNumber );
		       	}
	     	);
    	}else{
    		$scope.testNumber = new TestNumberService();
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
	    		TestNumberService.update($scope.testNumber, function(response) {	
	    			$translate('pages.TestNumber.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestNumberService.save($scope.testNumber,function(response) {
					
    				$translate('pages.TestNumber.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
	});
