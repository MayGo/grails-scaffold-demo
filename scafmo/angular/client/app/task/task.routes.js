'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.task', {
		    url: '/task',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.task.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/task/task.list.html',
			controller: 'TaskListController'
		}).state('app.task.create',{
			url: '/create',
			templateUrl: 'app/task/task.form.html',
			controller: 'TaskEditController',
			resolve:{
				taskData: function($stateParams, TaskService) {
					return new TaskService();
				}
			}
		}).state('app.task.edit',{
			url: '/edit/:id',
			templateUrl: 'app/task/task.form.html',
			controller: 'TaskEditController',
			resolve:{
				taskData: function($stateParams, TaskService){
					return TaskService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.task.view',{
			url: '/view/:id',
			templateUrl: 'app/task/task.view.html',
			controller: 'TaskViewController',
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