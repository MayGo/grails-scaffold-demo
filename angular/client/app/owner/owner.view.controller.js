'use strict';

angular.module('angularDemoApp')
    .controller('OwnerViewController', function ($scope, $state, $stateParams, $translate, inform, OwnerService) {
	 	$scope.owner = OwnerService.get({id:$stateParams.id});

		$scope.deleteOwner = function(instance){
			return OwnerService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});