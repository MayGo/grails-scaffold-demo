'use strict';

angular.module('angularDemoApp')
    .controller('TaskViewController', function ($scope, $state, $stateParams, $translate, inform, TaskService) {
	 	$scope.task = TaskService.get({id:$stateParams.id});

		$scope.deleteTask = function(instance){
			return TaskService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});