'use strict';

angular.module('angularDemoApp')
    .controller('PetTypeViewController', function ($scope, $state, $stateParams, $translate, inform, PetTypeService) {
	 	$scope.petType = PetTypeService.get({id:$stateParams.id});

		$scope.deletePetType = function(instance){
			return PetTypeService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});