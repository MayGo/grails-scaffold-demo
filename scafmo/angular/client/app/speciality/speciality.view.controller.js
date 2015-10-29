'use strict';

angular.module('angularDemoApp')
    .controller('SpecialityViewController', function ($scope, $state, $stateParams, $translate, inform,
            SpecialityService, specialityData,$mdDialog) {
	 	$scope.speciality = specialityData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteSpeciality = function(instance){
			return SpecialityService.deleteInstance(instance).then(function(instance){
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
