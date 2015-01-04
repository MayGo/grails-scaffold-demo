'use strict';



angular.module('angularDemoApp')
    .controller('VisitEditController', function ($scope, $state, $q, $stateParams, VisitService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		VisitService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.visit = angular.extend({}, $scope.visit , response);
			       	$scope.orig = angular.copy($scope.visit );
		       	}
	     	);
    	}else{
    		$scope.visit = new VisitService();
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
	    		VisitService.update($scope.visit, function(response) {	
	    			$translate('pages.Visit.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			VisitService.save($scope.visit,function(response) {
					
    				$translate('pages.Visit.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
	});
