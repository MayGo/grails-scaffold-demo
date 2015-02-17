'use strict';

angular.module('angularDemoApp')
    .controller('RoleViewController', function ($scope, $state, $stateParams, $translate, inform, RoleService) {
	 	$scope.role = RoleService.get({id:$stateParams.id});

		$scope.deleteRole = function(instance){
			return RoleService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});