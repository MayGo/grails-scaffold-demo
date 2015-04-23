'use strict';

angular.module('angularDemoApp')
    .controller('VetViewController', function ($scope, $state, $stateParams, $translate, inform, VetService, vetData) {
	 	$scope.vet = vetData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteVet = function(instance){
			return VetService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
