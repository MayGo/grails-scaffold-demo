'use strict';

angular.module('angularDemoApp')
    .controller('PersonCollectionlessViewController', function ($scope, $state, $stateParams, $translate, inform, PersonCollectionlessService) {
	 	$scope.personCollectionless = PersonCollectionlessService.get({id:$stateParams.id});

		$scope.deletePersonCollectionless = function(instance){
			return PersonCollectionlessService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});