'use strict';



angular.module('angularDemoApp')
    .controller('ClassifierEditController', function ($scope, $state, $q, $stateParams, ClassifierService, classifierData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.classifier = classifierData;


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
	    		ClassifierService.update($scope.classifier, function(response) {	
	    			$translate('pages.classifier.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			ClassifierService.save($scope.classifier,function(response) {
					
    				$translate('pages.classifier.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
	});
