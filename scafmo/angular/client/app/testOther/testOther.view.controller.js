'use strict';

angular.module('angularDemoApp')
    .controller('TestOtherViewController', function ($scope, $state, $stateParams, $translate, inform,
            TestOtherService, testOtherData,$mdDialog) {
	 	$scope.testOther = testOtherData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteTestOther = function(instance){
			return TestOtherService.deleteInstance(instance).then(function(instance){
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
