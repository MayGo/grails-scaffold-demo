'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.task', {
		    url: '/task',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.task.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.task': {
					templateUrl: 'app/task/task.list.html',
					controller: 'TaskListController'
				}
			}
		}).state('app.task.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.task.list'
			},
			views: {
				'page@app.task': {
					templateUrl: 'app/task/task.form.html',
					controller: 'TaskEditController'
				}
			},
			resolve:{
				taskData: function($stateParams, TaskService) {
					return new TaskService();
				}
			}
		}).state('app.task.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.task.list'
			},
			views: {
				'page@app.task': {
					templateUrl: 'app/task/task.view.html',
					controller: 'TaskViewController'
				}
			},
			resolve:{
				taskData: function($stateParams, TaskService){
					return TaskService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.task.view.edit',{
			url: '/edit',
			views: {
				'page@app.task': {
					templateUrl: 'app/task/task.form.html',
					controller: 'TaskEditController',
				}
			},
			resolve:{
				taskData: function($stateParams, TaskService){
					return TaskService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});