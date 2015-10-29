'use strict';

angular.module('angularDemoApp')
    .controller('DivisionCollectionViewController', function ($scope, $state, $stateParams, $translate, inform,
            DivisionCollectionService, divisionCollectionData,$mdDialog) {
	 	$scope.divisionCollection = divisionCollectionData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteDivisionCollection = function(instance){
			return DivisionCollectionService.deleteInstance(instance).then(function(instance){
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
