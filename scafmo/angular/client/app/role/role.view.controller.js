'use strict';

angular.module('angularDemoApp')
    .controller('RoleViewController', function ($scope, $state, $stateParams, $translate, inform,
            RoleService, roleData,$mdDialog) {
	 	$scope.role = roleData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteRole = function(instance){
			return RoleService.deleteInstance(instance).then(function(instance){
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
