'use strict';



angular.module('angularDemoApp')
    .controller('PersonCollectionEditController', function ($scope, $state, $q, $stateParams, PersonCollectionService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		PersonCollectionService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.personCollection = angular.extend({}, $scope.personCollection , response);
			       	$scope.orig = angular.copy($scope.personCollection );
		       	}
	     	);
    	}else{
    		$scope.personCollection = new PersonCollectionService();
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
	    		PersonCollectionService.update($scope.personCollection, function(response) {	
	    			$translate('pages.PersonCollection.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PersonCollectionService.save($scope.personCollection,function(response) {
					
    				$translate('pages.PersonCollection.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
