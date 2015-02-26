'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.visit', {
		    url: '/visit',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.visit.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/visit/visit.list.html',
			controller: 'VisitListController'
		}).state('app.visit.create',{
			url: '/create',
			templateUrl: 'app/visit/visit.form.html',
			controller: 'VisitEditController'
		}).state('app.visit.edit',{
			url: '/edit/:id',
			templateUrl: 'app/visit/visit.form.html',
			controller: 'VisitEditController'
		}).state('app.visit.view',{
			url: '/view/:id',
			templateUrl: 'app/visit/visit.view.html',
			controller: 'VisitViewController'
		});		
});