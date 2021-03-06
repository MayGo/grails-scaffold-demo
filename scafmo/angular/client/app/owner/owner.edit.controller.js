'use strict';



angular.module('angularDemoApp')
    .controller('OwnerEditController', function ($scope, $state, $q, $stateParams, OwnerService, ownerData, $translate, logger , PetService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.owner = ownerData;


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
	    		OwnerService.update($scope.owner, function(response) {	
	    			$translate('pages.owner.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			OwnerService.save($scope.owner,function(response) {
					
    				$translate('pages.owner.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
		
			 if($scope.isEditForm){
				PetService.query({max:50, owner:$stateParams.id, excludes:'visits,type,owner'}).$promise.then(
					function( response ){
						$scope.owner.pets = response.map(function(item){
							return {id:item.id, name:item.id+ ', ' +item.name+ ', ' +item.birthDate};
						});
					}
				);
			 }
			 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
			 $scope.$watch('owner.pets', function(values) {
				if(values && values.length>0){
					_.forEach(values, function(value) { value.owner={id:$stateParams.id}; });
				}
			 }, true);
		 	   
	});
