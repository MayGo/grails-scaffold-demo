'use strict';



angular.module('angularDemoApp')
    .controller('TagEditController', function ($scope, $state, $q, $stateParams, TagService, $translate, inform , TaskService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		TagService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.tag = angular.extend({}, $scope.tag , response);
			       	$scope.orig = angular.copy($scope.tag );
		       	}
	     	);
    	}else{
    		$scope.tag = new TagService();
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
	    		TagService.update($scope.tag, function(response) {	
	    			$translate('pages.Tag.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TagService.save($scope.tag,function(response) {
					
    				$translate('pages.Tag.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
		
	     if($scope.isEditForm){
			TaskService.query({filter:{null:$stateParams.id}, excludes:'tags'}).$promise.then(
		        function( response ){
			       	$scope.tag = angular.extend({}, $scope.tag);
	     			$scope.tag.tasks = response.map(function(item){
                        return {id:item.id, name:item.id};
				    });
		       	}
	     	);
	     	
		 }
		 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
     	 $scope.$watch('tag.tasks', function(values) {
     	 	if(values && values.length>0){
				_.forEach(values, function(value) { value.null={id:$stateParams.id}; });
		    }
	     }, true);
     	   
	});
