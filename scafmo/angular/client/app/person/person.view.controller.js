'use strict';

angular.module('angularDemoApp')
    .controller('PersonViewController', function ($scope, $state, $stateParams, $translate, inform, PersonService) {
	 	$scope.person = PersonService.get({id:$stateParams.id});

		$scope.deletePerson = function(instance){
			return PersonService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});