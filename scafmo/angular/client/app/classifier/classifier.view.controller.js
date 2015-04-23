'use strict';

angular.module('angularDemoApp')
    .controller('ClassifierViewController', function ($scope, $state, $stateParams, $translate, inform, ClassifierService, classifierData) {
	 	$scope.classifier = classifierData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteClassifier = function(instance){
			return ClassifierService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
