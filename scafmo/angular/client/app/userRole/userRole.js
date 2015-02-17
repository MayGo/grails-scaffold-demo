'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.userRole', {
		    url: '/userRole',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.userRole.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/userRole/userRole.list.html',
			controller: 'UserRoleListController'
		}).state('app.userRole.create',{
			url: '/create',
			templateUrl: 'app/userRole/userRole.form.html',
			controller: 'UserRoleEditController'
		}).state('app.userRole.edit',{
			url: '/edit/:id',
			templateUrl: 'app/userRole/userRole.form.html',
			controller: 'UserRoleEditController'
		}).state('app.userRole.view',{
			url: '/view/:id',
			templateUrl: 'app/userRole/userRole.view.html',
			controller: 'UserRoleViewController'
		});		
});