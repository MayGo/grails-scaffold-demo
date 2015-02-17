'use strict';



angular.module('angularDemoApp')
    .controller('PersonCollectionlessEditController', function ($scope, $state, $q, $stateParams, PersonCollectionlessService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		PersonCollectionlessService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.personCollectionless = angular.extend({}, $scope.personCollectionless , response);
			       	$scope.orig = angular.copy($scope.personCollectionless );
		       	}
	     	);
    	}else{
    		$scope.personCollectionless = new PersonCollectionlessService();
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
	    		PersonCollectionlessService.update($scope.personCollectionless, function(response) {	
	    			$translate('pages.PersonCollectionless.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PersonCollectionlessService.save($scope.personCollectionless,function(response) {
					
    				$translate('pages.PersonCollectionless.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
