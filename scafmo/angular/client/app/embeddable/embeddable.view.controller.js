'use strict';

angular.module('angularDemoApp')
    .controller('EmbeddableViewController', function ($scope, $state, $stateParams, $translate, inform,
            EmbeddableService, embeddableData,$mdDialog) {
	 	$scope.embeddable = embeddableData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteEmbeddable = function(instance){
			return EmbeddableService.deleteInstance(instance).then(function(instance){
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
