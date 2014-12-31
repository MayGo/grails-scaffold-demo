'use strict';

angular.module('angularDemoApp')
    .controller('DivisionCollectionlessViewController', function ($scope, $state, $stateParams, $translate, inform, DivisionCollectionlessService) {
	 	$scope.divisionCollectionless = DivisionCollectionlessService.get({id:$stateParams.id});

		$scope.deleteDivisionCollectionless = function(instance){
			return DivisionCollectionlessService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});