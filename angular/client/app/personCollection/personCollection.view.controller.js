'use strict';

angular.module('angularDemoApp')
    .controller('PersonCollectionViewController', function ($scope, $state, $stateParams, $translate, inform, PersonCollectionService) {
	 	$scope.personCollection = PersonCollectionService.get({id:$stateParams.id});

		$scope.deletePersonCollection = function(instance){
			return PersonCollectionService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});