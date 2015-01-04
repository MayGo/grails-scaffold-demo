'use strict';



angular.module('angularDemoApp')
    .controller('OwnerEditController', function ($scope, $state, $q, $stateParams, OwnerService, $translate, inform , PetService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		OwnerService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.owner = angular.extend({}, $scope.owner , response);
			       	$scope.orig = angular.copy($scope.owner );
		       	}
	     	);
    	}else{
    		$scope.owner = new OwnerService();
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
	    		OwnerService.update($scope.owner, function(response) {	
	    			$translate('pages.Owner.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			OwnerService.save($scope.owner,function(response) {
					
    				$translate('pages.Owner.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
		
	     if($scope.isEditForm){
			PetService.query({filter:{owner:$stateParams.id}, excludes:'visits,type,owner'}).$promise.then(
		        function( response ){
			       	$scope.owner = angular.extend({}, $scope.owner);
	     			$scope.owner.pets = response.map(function(item){
                        return {id:item.id, name:item.name};
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
