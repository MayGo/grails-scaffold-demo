'use strict';

angular.module('angularDemoApp')
    .controller('PersonCollectionViewController', function ($scope, $state, $stateParams, $translate, inform, PersonCollectionService, personCollectionData) {
	 	$scope.personCollection = personCollectionData;

		if($state.current.data){
			$scope.isModal = $state.current.data.isModal;
		}

		$scope.deletePersonCollection = function(instance){
			return PersonCollectionService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};
		$scope.go = function(route){
			$state.go(route);
		};
	});
