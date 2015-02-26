'use strict';



angular.module('angularDemoApp')
    .controller('DivisionCollectionEditController', function ($scope, $state, $q, $stateParams, DivisionCollectionService, $translate, inform , PersonCollectionService) {
    	$scope.isEditForm = ($stateParams.id)?true:false;
    	
    	if($scope.isEditForm){
    		DivisionCollectionService.get({id:$stateParams.id}).$promise.then(
		        function( response ){
			       	$scope.divisionCollection = angular.extend({}, $scope.divisionCollection , response);
			       	$scope.orig = angular.copy($scope.divisionCollection );
		       	}
	     	);
    	}else{
    		$scope.divisionCollection = new DivisionCollectionService();
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
	    		DivisionCollectionService.update($scope.divisionCollection, function(response) {	
	    			$translate('pages.DivisionCollection.messages.update').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			DivisionCollectionService.save($scope.divisionCollection,function(response) {
					
    				$translate('pages.DivisionCollection.messages.create').then(function (msg) {
				    	inform.add(msg, {'type': 'success'});
					});
					deferred.resolve(response);
            	 	$state.go('^.view', { id: response.id });
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
		
	     if($scope.isEditForm){
			PersonCollectionService.query({filter:{division:$stateParams.id}, excludes:'division'}).$promise.then(
		        function( response ){
			       	$scope.divisionCollection = angular.extend({}, $scope.divisionCollection);
	     			$scope.divisionCollection.persons = response.map(function(item){
                        return {id:item.id, name:item.id+ ', ' +item.name+ ', ' +item.age};
				    });
		       	}
	     	);
	     	
		 }
		 //Watch for oneToMany property, to add custom object to each value. Without this, adding elements have no effect when POSTing.
     	 $scope.$watch('divisionCollection.persons', function(values) {
     	 	if(values && values.length>0){
				_.forEach(values, function(value) { value.division={id:$stateParams.id}; });
		    }
	     }, true);
     	   
			   
	});
