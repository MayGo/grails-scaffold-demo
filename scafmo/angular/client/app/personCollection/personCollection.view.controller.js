'use strict';

angular.module('angularDemoApp')
    .controller('PersonCollectionViewController', function ($scope, $state, $stateParams, $translate,
            PersonCollectionService, personCollectionData,$mdDialog) {
	 	$scope.personCollection = personCollectionData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePersonCollection = function(instance){
			return PersonCollectionService.deleteInstance(instance).then(function(instance){
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
