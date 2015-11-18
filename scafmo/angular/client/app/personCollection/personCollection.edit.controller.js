'use strict';



angular.module('angularDemoApp')
    .controller('PersonCollectionEditController', function ($scope, $state, $q, $stateParams, PersonCollectionService, personCollectionData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.personCollection = personCollectionData;


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
	    		PersonCollectionService.update($scope.personCollection, function(response) {	
	    			$translate('pages.personCollection.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PersonCollectionService.save($scope.personCollection,function(response) {
					
    				$translate('pages.personCollection.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
