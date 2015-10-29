'use strict';

angular.module('angularDemoApp')
    .controller('PetViewController', function ($scope, $state, $stateParams, $translate, inform,
            PetService, petData,$mdDialog) {
	 	$scope.pet = petData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePet = function(instance){
			return PetService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
		$scope.closeItemViewer = function () {
			$mdDialog.hide();
		};
	});
