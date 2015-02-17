'use strict';



angular.module('angularDemoApp')
    .controller('ClassifierEditController', function ($scope, $state, $q, $stateParams, ClassifierService, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		ClassifierService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.classifier = angular.extend({}, $scope.classifier , response);
			       	$scope.orig = angular.copy($scope.classifier );
		       	}
	     	);
    	}else{
    		$scope.classifier = new ClassifierService();
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
	    		ClassifierService.update($scope.classifier, function(response) {	
	    			$translate('pages.Classifier.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			ClassifierService.save($scope.classifier,function(response) {
					
    				$translate('pages.Classifier.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
	});
