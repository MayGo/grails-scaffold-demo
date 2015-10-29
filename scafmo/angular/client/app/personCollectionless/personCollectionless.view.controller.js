'use strict';

angular.module('angularDemoApp')
    .controller('PersonCollectionlessViewController', function ($scope, $state, $stateParams, $translate, inform,
            PersonCollectionlessService, personCollectionlessData,$mdDialog) {
	 	$scope.personCollectionless = personCollectionlessData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePersonCollectionless = function(instance){
			return PersonCollectionlessService.deleteInstance(instance).then(function(instance){
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
