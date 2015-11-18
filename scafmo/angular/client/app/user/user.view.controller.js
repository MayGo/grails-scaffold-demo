'use strict';

angular.module('angularDemoApp')
    .controller('UserViewController', function ($scope, $state, $stateParams, $translate,
            UserService, userData,$mdDialog) {
	 	$scope.user = userData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteUser = function(instance){
			return UserService.deleteInstance(instance).then(function(instance){
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
