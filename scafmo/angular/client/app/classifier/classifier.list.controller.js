'use strict';

angular.module('angularDemoApp')
	.controller('ClassifierListController', function ($scope, $rootScope, $state, $q, ClassifierService, $stateParams, $timeout) {

	if($state.current.data){
		$scope.isTab = $state.current.data.isTab;
	}

	$scope.deleteClassifier = function(instance){
		return ClassifierService.deleteInstance(instance).then(function(instance){
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
		filterTimeout = $timeout(function() {
			var query = {max: $scope.stTable.itemsByPage, offset: tableState.pagination.start};
			if (tableState.sort.predicate) {
				query.order = tableState.sort.reverse ? 'asc' : 'desc';
				query.sort = tableState.sort.predicate;
			}

			var searchParams = tableState.search.predicateObject;
			query.filter = {};

			if (searchParams) {
				angular.forEach(searchParams, function(value, key) {
					if(!_.isEmpty(value)){
						this[key] = value;
					}
				}, query.filter);
			}

			if($stateParams.relationName && $stateParams.id){
				if(_.isEmpty(query.filter[$stateParams.relationName])){
					query.filter[$stateParams.relationName] = [];
				}
				query.filter[$stateParams.relationName].push(Number($stateParams.id));
			}
			if (filterTimeout){
				$timeout.cancel(filterTimeout);
			}


			ClassifierService.query(query, function(response, responseHeaders){
				$scope.isLoading = false;
				$scope.rowCollection = response;
				tableState.pagination.numberOfPages = Math.ceil(responseHeaders().total / tableState.pagination.number);
			});
		}, 255);

	};
});
