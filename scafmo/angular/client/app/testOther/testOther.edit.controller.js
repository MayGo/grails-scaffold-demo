'use strict';



angular.module('angularDemoApp')
    .controller('TestOtherEditController', function ($scope, $state, $q, $stateParams, TestOtherService, testOtherData, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.testOther = testOtherData;


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
	    		TestOtherService.update($scope.testOther, function(response) {	
	    			$translate('pages.testOther.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestOtherService.save($scope.testOther,function(response) {
					
    				$translate('pages.testOther.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
	});
