'use strict';

angular.module('angularDemoApp')
	.controller('UserListController', function ($scope, $q, UserService, $translate, inform) {
		
	$scope.deleteUser = function(instance){
		return UserService.deleteInstance(instance).then(function(instance){
			var index = $scope.rowCollection.indexOf(instance);
			if (index !== -1) {
				$scope.rowCollection.splice(index, 1);
			}
			return instance;
		});
	};
	
	
	$scope.isLoading = false;
	$scope.rowCollection = [];
	
	$scope.callServer = function (tableState) {
		
		var query = {max: $scope.stTable.itemsByPage, offset: tableState.pagination.start};
		if (tableState.sort.predicate) {
			query.order = tableState.sort.reverse ? 'asc' : 'desc';
			query.sort = tableState.sort.predicate;
		}

		var searchParams = tableState.search.predicateObject;
		if (searchParams) {
			query.filter = {};
			angular.forEach(searchParams, function(value, key) {
				if(!_.isEmpty(value)){
					this[key] = value;
				}
			}, query.filter);
		}
		
		if(!$scope.skipFirstQueryInEmbeddedView ){
			UserService.query(query, function(response, responseHeaders){
				$scope.isLoading = false;
				$scope.rowCollection = response;
				tableState.pagination.numberOfPages = Math.ceil(responseHeaders().total / tableState.pagination.number);
			});
		}else{
			$scope.skipFirstQueryInEmbeddedView = null;
		}
	};

});
//Simple Controller to make new scope for ListController
angular.module('angularDemoApp')
	.controller('UserEmbeddedListController', function ($scope) {
	$scope.isEmbeddedView = true;
	$scope.skipFirstQueryInEmbeddedView = true;
});
