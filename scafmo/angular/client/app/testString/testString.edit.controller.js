'use strict';



angular.module('angularDemoApp')
    .controller('TestStringEditController', function ($scope, $state, $q, $stateParams, TestStringService, testStringData, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.testString = testStringData;


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
	    		TestStringService.update($scope.testString, function(response) {	
	    			$translate('pages.testString.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestStringService.save($scope.testString,function(response) {
					
    				$translate('pages.testString.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
	});
