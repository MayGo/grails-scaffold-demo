'use strict';

angular.module('angularDemoApp')
	.controller('TaskListController', function ($scope, $rootScope,
		$state, $q, TaskService, $stateParams, $timeout, inform, ngTableParams, appConfig) {

	if($state.current.data){
		$scope.isTab = $state.current.data.isTab;
	}

	$scope.deleteTask = function(instance){
		return TaskService.deleteInstance(instance).then(function(instance){
			$scope.tableParams.reload();
			return instance;
		});
	};


	$scope.search = {};

	var filterTimeout;
	$scope.tableParams = new ngTableParams({
		page: 1,            // show first page
		count: 10,          // count per page
		sorting: {
			id: 'asc'     // initial sorting
		},
		filter: $scope.search
	}, {
		total: 0,           // length of data
		getData: function($defer, params) {

			// do not let to make do much queries
			if (filterTimeout){
				$timeout.cancel(filterTimeout);
			}

			filterTimeout = $timeout(function() {
				var offset = (params.page()-1) * params.count();
				var paging = {max: appConfig.itemsByPage, offset: offset};

				var query = _.merge(paging, params.filter())

				if (params.sorting()) {
					query.sort = Object.keys(params.sorting())[0];
					query.order = params.sorting()[query.sort];
				}


				if($stateParams.relationName && $stateParams.id){
					if(_.isEmpty(query[$stateParams.relationName])){
						query[$stateParams.relationName] = [];
					}
					query[$stateParams.relationName].push(Number($stateParams.id));
				}

				var errorCallback = function(response){
					if (response.data && response.data.errors) {
						angular.forEach(response.data.errors, function (error) {
							inform.add(error.message, {ttl: -1,'type': 'warning'});
						});
					}
				};

				TaskService.query(query, function(response, responseHeaders){
					params.total(responseHeaders().total);
					$defer.resolve(response);
				}, errorCallback);
			}, 255);

		}
	});

});
