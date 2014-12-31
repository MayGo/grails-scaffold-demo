'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.role', {
		    url: '/role',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.role.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/role/role.list.html',
			controller: 'RoleListController'
		}).state('app.role.create',{
			url: '/create',
			templateUrl: 'app/role/role.form.html',
			controller: 'RoleEditController'
		}).state('app.role.edit',{
			url: '/edit/:id',
			templateUrl: 'app/role/role.form.html',
			controller: 'RoleEditController'
		}).state('app.role.view',{
			url: '/view/:id',
			templateUrl: 'app/role/role.view.html',
			controller: 'RoleViewController'
		});		
});