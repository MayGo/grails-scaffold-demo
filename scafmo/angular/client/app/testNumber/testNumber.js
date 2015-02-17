'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testNumber', {
		    url: '/testNumber',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.testNumber.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/testNumber/testNumber.list.html',
			controller: 'TestNumberListController'
		}).state('app.testNumber.create',{
			url: '/create',
			templateUrl: 'app/testNumber/testNumber.form.html',
			controller: 'TestNumberEditController'
		}).state('app.testNumber.edit',{
			url: '/edit/:id',
			templateUrl: 'app/testNumber/testNumber.form.html',
			controller: 'TestNumberEditController'
		}).state('app.testNumber.view',{
			url: '/view/:id',
			templateUrl: 'app/testNumber/testNumber.view.html',
			controller: 'TestNumberViewController'
		});		
});