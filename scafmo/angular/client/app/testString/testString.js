'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testString', {
		    url: '/testString',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.testString.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/testString/testString.list.html',
			controller: 'TestStringListController'
		}).state('app.testString.create',{
			url: '/create',
			templateUrl: 'app/testString/testString.form.html',
			controller: 'TestStringEditController'
		}).state('app.testString.edit',{
			url: '/edit/:id',
			templateUrl: 'app/testString/testString.form.html',
			controller: 'TestStringEditController'
		}).state('app.testString.view',{
			url: '/view/:id',
			templateUrl: 'app/testString/testString.view.html',
			controller: 'TestStringViewController'
		});		
});