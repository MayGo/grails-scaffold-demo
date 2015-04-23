'use strict';

angular.module('angularDemoApp')
    .controller('PetTypeViewController', function ($scope, $state, $stateParams, $translate, inform, PetTypeService, petTypeData) {
	 	$scope.petType = petTypeData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePetType = function(instance){
			return PetTypeService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
