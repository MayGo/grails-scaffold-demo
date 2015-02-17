'use strict';

angular.module('angularDemoApp')
    .controller('DivisionCollectionViewController', function ($scope, $state, $stateParams, $translate, inform, DivisionCollectionService) {
	 	$scope.divisionCollection = DivisionCollectionService.get({id:$stateParams.id});

		$scope.deleteDivisionCollection = function(instance){
			return DivisionCollectionService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});