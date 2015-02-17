'use strict';

angular.module('angularDemoApp')
    .controller('UserViewController', function ($scope, $state, $stateParams, $translate, inform, UserService) {
	 	$scope.user = UserService.get({id:$stateParams.id});

		$scope.deleteUser = function(instance){
			return UserService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});