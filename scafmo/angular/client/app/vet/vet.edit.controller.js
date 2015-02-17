'use strict';



angular.module('angularDemoApp')
    .controller('VetEditController', function ($scope, $state, $q, $stateParams, VetService, $translate, inform , SpecialityService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		VetService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.vet = angular.extend({}, $scope.vet , response);
			       	$scope.orig = angular.copy($scope.vet );
		       	}
	     	);
    	}else{
    		$scope.vet = new VetService();
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
	    		VetService.update($scope.vet, function(response) {	
	    			$translate('pages.Vet.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			VetService.save($scope.vet,function(response) {
					
    				$translate('pages.Vet.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
		
	     if($scope.isEditForm){
			SpecialityService.query({filter:{null:$stateParams.id}, excludes:''}).$promise.then(
		        function( response ){
			       	$scope.vet = angular.extend({}, $scope.vet);
	     			$scope.vet.specialities = response.map(function(item){
                        return {id:item.id, name:item.id+ ', ' +item.name};
				    });
		       	}
	     	);
	     	
		 }
		 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
     	 $scope.$watch('vet.specialities', function(values) {
     	 	if(values && values.length>0){
				_.forEach(values, function(value) { value.null={id:$stateParams.id}; });
		    }
	     }, true);
     	   
	});
