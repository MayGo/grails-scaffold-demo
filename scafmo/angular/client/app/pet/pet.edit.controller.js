'use strict';



angular.module('angularDemoApp')
    .controller('PetEditController', function ($scope, $state, $q, $stateParams, PetService, $translate, inform , VisitService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		PetService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.pet = angular.extend({}, $scope.pet , response);
			       	$scope.orig = angular.copy($scope.pet );
		       	}
	     	);
    	}else{
    		$scope.pet = new PetService();
    	}
		
	
	    $scope.submit = function(frmController) {
			var deferred = $q.defer();
	    	var errorCallback = function(response){
					if (response.data.errors) {
		                angular.forEach(response.data.errors, function (error) {
		                    frmController.setExternalValidation(error.field, undefined, error.message);
		                });
		            }
					deferred.reject(response);
		       };
	       
	    	if($scope.isEditForm){
	    		PetService.update($scope.pet, function(response) {	
	    			$translate('pages.Pet.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PetService.save($scope.pet,function(response) {
					
    				$translate('pages.Pet.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
		
	     if($scope.isEditForm){
			VisitService.query({filter:{pet:$stateParams.id}, excludes:'pet'}).$promise.then(
		        function( response ){
			       	$scope.pet = angular.extend({}, $scope.pet);
	     			$scope.pet.visits = response.map(function(item){
                        return {id:item.id, name:item.id+ ', ' +item.date+ ', ' +item.description};
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
