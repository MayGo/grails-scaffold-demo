'use strict';

angular.module('angularDemoApp')
    .controller('OwnerViewController', function ($scope, $state, $stateParams, $translate, inform, OwnerService, ownerData) {
	 	$scope.owner = ownerData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteOwner = function(instance){
			return OwnerService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
