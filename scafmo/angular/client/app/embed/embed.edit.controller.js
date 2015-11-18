'use strict';



angular.module('angularDemoApp')
    .controller('EmbedEditController', function ($scope, $state, $q, $stateParams, EmbedService, embedData, $translate, logger ) {
    	$scope.isEditForm = ($stateParams.id)?true:false;

		$scope.embed = embedData;


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
	    		EmbedService.update($scope.embed, function(response) {	
	    			$translate('pages.embed.messages.update').then(function (msg) {
				    	logger.info(msg);
					});
	            	deferred.resolve(response);
		        },errorCallback);
	    	}else{
    			EmbedService.save($scope.embed,function(response) {
					
    				$translate('pages.embed.messages.create').then(function (msg) {
				    	logger.info(msg);
					});
					$state.go('^.view', { id: response.id }, {location: 'replace'});
					deferred.resolve(response);
		        },errorCallback);
	    	}
	        return deferred.promise;
	    };
       
			   
			   
			   
			   
			   
	});
