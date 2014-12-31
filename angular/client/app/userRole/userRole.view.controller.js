'use strict';

angular.module('angularDemoApp')
    .controller('UserRoleViewController', function ($scope, $state, $stateParams, $translate, inform, UserRoleService) {
	 	$scope.userRole = UserRoleService.get({id:$stateParams.id});

		$scope.deleteUserRole = function(instance){
			return UserRoleService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});