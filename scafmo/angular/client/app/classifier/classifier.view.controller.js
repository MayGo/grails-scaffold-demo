'use strict';

angular.module('angularDemoApp')
    .controller('ClassifierViewController', function ($scope, $state, $stateParams, $translate, inform, ClassifierService) {
	 	$scope.classifier = ClassifierService.get({id:$stateParams.id});

		$scope.deleteClassifier = function(instance){
			return ClassifierService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});