'use strict';



angular.module('angularDemoApp')
    .controller('PersonEditController', function ($scope, $state, $q, $stateParams, PersonService, personData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.person = personData;


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
	    		PersonService.update($scope.person, function(response) {	
	    			$translate('pages.person.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PersonService.save($scope.person,function(response) {
					
    				$translate('pages.person.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
