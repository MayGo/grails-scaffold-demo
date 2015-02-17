'use strict';

angular.module('angularDemoApp')
    .controller('SpecialityViewController', function ($scope, $state, $stateParams, $translate, inform, SpecialityService) {
	 	$scope.speciality = SpecialityService.get({id:$stateParams.id});

		$scope.deleteSpeciality = function(instance){
			return SpecialityService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});