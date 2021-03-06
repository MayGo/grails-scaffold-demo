'use strict';



angular.module('angularDemoApp')
    .controller('TagEditController', function ($scope, $state, $q, $stateParams, TagService, tagData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.tag = tagData;


	    $scope.submit = function(frmController) {
			var deferred = $q.defer();
	    	var errorCallback = function(response){
					if (response.data.errors) {
		                angular.forEach(response.data.errors, function (error) {
							if(angular.element('#'+error.field).length) {
								frmController.setExternalValidation(error.field, undefined, error.message);
							} else {
								logger.error(error.message);
							}
		                });
		            }
					deferred.reject(response);
		       };

	    	if($scope.isEditForm){
	    		TagService.update($scope.tag, function(response) {	
	    			$translate('pages.tag.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			TagService.save($scope.tag,function(response) {
					
    				$translate('pages.tag.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
		
	if($scope.tag.tasks){
		$scope.tag.tasks = $scope.tag.tasks.map(function(item){
			return {id:item.id, name:item.id+ ', ' +item.timeSpent+ ', ' +item.summary+ ', ' +item.status};
		});
	}else{
		$scope.tag.tasks = [];
	}
		   
	});
