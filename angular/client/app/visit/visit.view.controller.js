'use strict';

angular.module('angularDemoApp')
    .controller('VisitViewController', function ($scope, $state, $stateParams, $translate, inform, VisitService) {
	 	$scope.visit = VisitService.get({id:$stateParams.id});

		$scope.deleteVisit = function(instance){
			return VisitService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});