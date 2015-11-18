'use strict';



angular.module('angularDemoApp')
    .controller('VetEditController', function ($scope, $state, $q, $stateParams, VetService, vetData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.vet = vetData;


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
	    		VetService.update($scope.vet, function(response) {	
	    			$translate('pages.vet.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			VetService.save($scope.vet,function(response) {
					
    				$translate('pages.vet.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
		
	if($scope.vet.specialities){
		$scope.vet.specialities = $scope.vet.specialities.map(function(item){
			return {id:item.id, name:item.id+ ', ' +item.name};
		});
	}else{
		$scope.vet.specialities = [];
	}
		   
	});
