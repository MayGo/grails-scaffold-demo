'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.user', {
		    url: '/user',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.user.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/user/user.list.html',
			controller: 'UserListController'
		}).state('app.user.create',{
			url: '/create',
			templateUrl: 'app/user/user.form.html',
			controller: 'UserEditController'
		}).state('app.user.edit',{
			url: '/edit/:id',
			templateUrl: 'app/user/user.form.html',
			controller: 'UserEditController'
		}).state('app.user.view',{
			url: '/view/:id',
			templateUrl: 'app/user/user.view.html',
			controller: 'UserViewController'
		});		
});