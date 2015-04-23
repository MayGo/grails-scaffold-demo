'use strict';

angular.module('angularDemoApp')
    .controller('DivisionCollectionlessViewController', function ($scope, $state, $stateParams, $translate, inform, DivisionCollectionlessService, divisionCollectionlessData) {
	 	$scope.divisionCollectionless = divisionCollectionlessData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteDivisionCollectionless = function(instance){
			return DivisionCollectionlessService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
