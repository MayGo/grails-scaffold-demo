'use strict';

angular.module('angularDemoApp')
    .controller('EmbedViewController', function ($scope, $state, $stateParams, $translate,
            EmbedService, embedData,$mdDialog) {
	 	$scope.embed = embedData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteEmbed = function(instance){
			return EmbedService.deleteInstance(instance).then(function(instance){
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
