'use strict';

angular.module('angularDemoApp')
    .controller('VetViewController', function ($scope, $state, $stateParams, $translate, inform, VetService) {
	 	$scope.vet = VetService.get({id:$stateParams.id});

		$scope.deleteVet = function(instance){
			return VetService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});