'use strict';

angular.module('angularDemoApp')
    .controller('TestOtherViewController', function ($scope, $state, $stateParams, $translate, inform, TestOtherService) {
	 	$scope.testOther = TestOtherService.get({id:$stateParams.id});

		$scope.deleteTestOther = function(instance){
			return TestOtherService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});