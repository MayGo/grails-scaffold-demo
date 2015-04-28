'use strict';

angular.module('angularDemoApp')
	.controller('TaskListController', function ($scope, $rootScope,
		$state, $q, TaskService, $stateParams, $timeout, inform) {

	if($state.current.data){
		$scope.isTab = $state.current.data.isTab;
	}

	$scope.deleteTask = function(instance){
		return TaskService.deleteInstance(instance).then(function(instance){
			var index = $scope.rowCollection.indexOf(instance);
			if (index !== -1) {
				$scope.rowCollection.splice(index, 1);
			}
			return instance;
		});
	};

	$scope.isLoading = true;
	$scope.rowCollection = [];
	var filterTimeout;
	$scope.callServer = function (tableState) {

		// do not let to make do much queries
		if (filterTimeout){
			$timeout.cancel(filterTimeout);
		}

		filterTimeout = $timeout(function() {
			var query = {max: $scope.stTable.itemsByPage, offset: tableState.pagination.start};
			if (tableState.sort.predicate) {
				query.order = tableState.sort.reverse ? 'asc' : 'desc';
				query.sort = tableState.sort.predicate;
			}

			var searchParams = tableState.search.predicateObject;

			if (searchParams) {
				angular.forEach(searchParams, function(value, key) {
					if(!_.isEmpty(value)){
						this[key] = value;
					}
				}, query);
			}

			if($stateParams.relationName && $stateParams.id){
				if(_.isEmpty(query[$stateParams.relationName])){
					query[$stateParams.relationName] = [];
				}
				query[$stateParams.relationName].push(Number($stateParams.id));
			}

			var errorCallback = function(response){
				if (response.data.errors) {
					angular.forEach(response.data.errors, function (error) {
						inform.add(error.message, {ttl: -1,'type': 'warning'});
					});
				}
			};

			TaskService.query(query, function(response, responseHeaders){
				$scope.isLoading = false;
				$scope.rowCollection = response;
				tableState.pagination.numberOfPages = Math.ceil(responseHeaders().total / tableState.pagination.number);
			}, errorCallback);
		}, 255);

	};
});
