'use strict';

angular.module('angularDemoApp')
    .controller('TestStringViewController', function ($scope, $state, $stateParams, $translate, inform,
            TestStringService, testStringData,$mdDialog) {
	 	$scope.testString = testStringData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteTestString = function(instance){
			return TestStringService.deleteInstance(instance).then(function(instance){
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
