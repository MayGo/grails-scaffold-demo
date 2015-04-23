'use strict';

angular.module('angularDemoApp')
    .controller('UserRoleViewController', function ($scope, $state, $stateParams, $translate, inform, UserRoleService, userRoleData) {
	 	$scope.userRole = userRoleData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteUserRole = function(instance){
			return UserRoleService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
