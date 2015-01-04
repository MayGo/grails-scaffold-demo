'use strict';

angular.module('angularDemoApp')
    .controller('PetViewController', function ($scope, $state, $stateParams, $translate, inform, PetService) {
	 	$scope.pet = PetService.get({id:$stateParams.id});

		$scope.deletePet = function(instance){
			return PetService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});