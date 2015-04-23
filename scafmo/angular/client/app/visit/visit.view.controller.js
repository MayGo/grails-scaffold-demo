'use strict';

angular.module('angularDemoApp')
    .controller('VisitViewController', function ($scope, $state, $stateParams, $translate, inform, VisitService, visitData) {
	 	$scope.visit = visitData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deleteVisit = function(instance){
			return VisitService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
