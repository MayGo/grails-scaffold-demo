'use strict';

angular.module('angularDemoApp')
    .controller('TagViewController', function ($scope, $state, $stateParams, $translate, inform, TagService) {
	 	$scope.tag = TagService.get({id:$stateParams.id});

		$scope.deleteTag = function(instance){
			return TagService.deleteInstance(instance).then(function(instance){
				$state.go('^.list');
				return instance;
			});
		};

	});