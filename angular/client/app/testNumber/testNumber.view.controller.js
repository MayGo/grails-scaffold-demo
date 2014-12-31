'use strict';

angular.module('angularDemoApp')
    .controller('TestNumberViewController', function ($scope, $state, $stateParams, $translate, inform, TestNumberService) {
	 	$scope.testNumber = TestNumberService.get({id:$stateParams.id});

		$scope.deleteTestNumber = function(instance){
			return TestNumberService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});