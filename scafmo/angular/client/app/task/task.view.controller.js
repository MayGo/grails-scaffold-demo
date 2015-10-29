'use strict';

angular.module('angularDemoApp')
    .controller('TaskViewController', function ($scope, $state, $stateParams, $translate, inform,
            TaskService, taskData,$mdDialog) {
	 	$scope.task = taskData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteTask = function(instance){
			return TaskService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
		$scope.closeItemViewer = function () {
			$mdDialog.hide();
		};
	});
