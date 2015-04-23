'use strict';

angular.module('angularDemoApp')
    .controller('PersonViewController', function ($scope, $state, $stateParams, $translate, inform, PersonService, personData) {
	 	$scope.person = personData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePerson = function(instance){
			return PersonService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
