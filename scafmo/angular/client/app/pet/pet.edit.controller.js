'use strict';



angular.module('angularDemoApp')
    .controller('PetEditController', function ($scope, $state, $q, $stateParams, PetService, petData, $translate, logger , VisitService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.pet = petData;


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
	    		PetService.update($scope.pet, function(response) {	
	    			$translate('pages.pet.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PetService.save($scope.pet,function(response) {
					
    				$translate('pages.pet.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
		
			 if($scope.isEditForm){
				VisitService.query({max:50, pet:$stateParams.id, excludes:'pet'}).$promise.then(
					function( response ){
						$scope.pet.visits = response.map(function(item){
							return {id:item.id, name:item.id+ ', ' +item.description+ ', ' +item.date};
						});
					}
				);
			 }
			 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
			 $scope.$watch('pet.visits', function(values) {
				if(values && values.length>0){
					_.forEach(values, function(value) { value.pet={id:$stateParams.id}; });
				}
			 }, true);
		 	   
			   
			   
	});
