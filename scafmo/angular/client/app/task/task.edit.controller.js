'use strict';



angular.module('angularDemoApp')
    .controller('TaskEditController', function ($scope, $state, $q, $stateParams, TaskService, taskData, $translate, inform ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.task = taskData;


	    $scope.submit = function(frmController) {
			var deferred = $q.defer();
	    	var errorCallback = function(response){
					if (response.data.errors) {
		                angular.forEach(response.data.errors, function (error) {
							if(angular.element('#'+error.field).length) {
								frmController.setExternalValidation(error.field, undefined, error.message);
							} else {
								inform.add(error.message, {ttl: -1,'type': 'warning'});
							}
		                });
		            }
					deferred.reject(response);
		       };

	    	if($scope.isEditForm){
	    		TaskService.update($scope.task, function(response) {	
	    			$translate('pages.task.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TaskService.save($scope.task,function(response) {
					
    				$translate('pages.task.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
			   
		
	if($scope.task.tags){
		$scope.task.tags = $scope.task.tags.map(function(item){
			return {id:item.id, name:item.id+ ', ' +item.name};
		});
	}else{
		$scope.task.tags = [];
	}
		   
	});
