'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testOther', {
		    url: '/testOther',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.testOther.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/testOther/testOther.list.html',
			controller: 'TestOtherListController'
		}).state('app.testOther.create',{
			url: '/create',
			templateUrl: 'app/testOther/testOther.form.html',
			controller: 'TestOtherEditController'
		}).state('app.testOther.edit',{
			url: '/edit/:id',
			templateUrl: 'app/testOther/testOther.form.html',
			controller: 'TestOtherEditController'
		}).state('app.testOther.view',{
			url: '/view/:id',
			templateUrl: 'app/testOther/testOther.view.html',
			controller: 'TestOtherViewController'
		});		
});