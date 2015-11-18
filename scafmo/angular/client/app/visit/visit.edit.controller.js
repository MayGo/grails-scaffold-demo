'use strict';



angular.module('angularDemoApp')
    .controller('VisitEditController', function ($scope, $state, $q, $stateParams, VisitService, visitData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.visit = visitData;


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
	    		VisitService.update($scope.visit, function(response) {	
	    			$translate('pages.visit.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			VisitService.save($scope.visit,function(response) {
					
    				$translate('pages.visit.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
