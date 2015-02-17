'use strict';



angular.module('angularDemoApp')
    .controller('TestStringEditController', function ($scope, $state, $q, $stateParams, TestStringService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		TestStringService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.testString = angular.extend({}, $scope.testString , response);
			       	$scope.orig = angular.copy($scope.testString );
		       	}
	     	);
    	}else{
    		$scope.testString = new TestStringService();
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
	    		TestStringService.update($scope.testString, function(response) {	
	    			$translate('pages.TestString.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestStringService.save($scope.testString,function(response) {
					
    				$translate('pages.TestString.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
	});
