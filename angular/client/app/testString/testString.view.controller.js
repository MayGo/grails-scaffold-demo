'use strict';

angular.module('angularDemoApp')
    .controller('TestStringViewController', function ($scope, $state, $stateParams, $translate, inform, TestStringService) {
	 	$scope.testString = TestStringService.get({id:$stateParams.id});

		$scope.deleteTestString = function(instance){
			return TestStringService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});