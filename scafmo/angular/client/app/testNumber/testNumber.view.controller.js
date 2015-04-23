'use strict';

angular.module('angularDemoApp')
    .controller('TestNumberViewController', function ($scope, $state, $stateParams, $translate, inform, TestNumberService, testNumberData) {
	 	$scope.testNumber = testNumberData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteTestNumber = function(instance){
			return TestNumberService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
