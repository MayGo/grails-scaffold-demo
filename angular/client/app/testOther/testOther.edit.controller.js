'use strict';



angular.module('angularDemoApp')
    .controller('TestOtherEditController', function ($scope, $state, $q, $stateParams, TestOtherService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		TestOtherService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.testOther = angular.extend({}, $scope.testOther , response);
			       	$scope.orig = angular.copy($scope.testOther );
		       	}
	     	);
    	}else{
    		$scope.testOther = new TestOtherService();
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
	    		TestOtherService.update($scope.testOther, function(response) {	
	    			$translate('pages.TestOther.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestOtherService.save($scope.testOther,function(response) {
					
    				$translate('pages.TestOther.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
	});
