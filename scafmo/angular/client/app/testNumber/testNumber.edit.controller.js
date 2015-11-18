'use strict';



angular.module('angularDemoApp')
    .controller('TestNumberEditController', function ($scope, $state, $q, $stateParams, TestNumberService, testNumberData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.testNumber = testNumberData;


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
	    		TestNumberService.update($scope.testNumber, function(response) {	
	    			$translate('pages.testNumber.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TestNumberService.save($scope.testNumber,function(response) {
					
    				$translate('pages.testNumber.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
	});
