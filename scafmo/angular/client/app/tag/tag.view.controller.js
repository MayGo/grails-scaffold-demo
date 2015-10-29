'use strict';

angular.module('angularDemoApp')
    .controller('TagViewController', function ($scope, $state, $stateParams, $translate, inform,
            TagService, tagData,$mdDialog) {
	 	$scope.tag = tagData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteTag = function(instance){
			return TagService.deleteInstance(instance).then(function(instance){
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
