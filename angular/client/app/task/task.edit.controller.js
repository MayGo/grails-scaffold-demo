'use strict';



angular.module('angularDemoApp')
    .controller('TaskEditController', function ($scope, $state, $q, $stateParams, TaskService, $translate, inform , TagService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		TaskService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.task = angular.extend({}, $scope.task , response);
			       	$scope.orig = angular.copy($scope.task );
		       	}
	     	);
    	}else{
    		$scope.task = new TaskService();
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
	    		TaskService.update($scope.task, function(response) {	
	    			$translate('pages.Task.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TaskService.save($scope.task,function(response) {
					
    				$translate('pages.Task.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
		
	     if($scope.isEditForm){
			TagService.query({filter:{null:$stateParams.id}, excludes:'tasks'}).$promise.then(
		        function( response ){
			       	$scope.task = angular.extend({}, $scope.task);
	     			$scope.task.tags = response.map(function(item){
                        return {id:item.id, name:item.name};
				    });
		       	}
	     	);
	     	
		 }
		 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
     	 $scope.$watch('task.tags', function(values) {
     	 	if(values && values.length>0){
				_.forEach(values, function(value) { value.null={id:$stateParams.id}; });
		    }
	     }, true);
     	   
	});
