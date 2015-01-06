'use strict';



angular.module('angularDemoApp')
    .controller('PersonEditController', function ($scope, $state, $q, $stateParams, PersonService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		PersonService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.person = angular.extend({}, $scope.person , response);
			       	$scope.orig = angular.copy($scope.person );
		       	}
	     	);
    	}else{
    		$scope.person = new PersonService();
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
	    		PersonService.update($scope.person, function(response) {	
	    			$translate('pages.Person.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			PersonService.save($scope.person,function(response) {
					
    				$translate('pages.Person.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
	});
