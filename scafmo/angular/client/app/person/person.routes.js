'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.person', {
		    url: '/person',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.person.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/person/person.list.html',
			controller: 'PersonListController'
		}).state('app.person.create',{
			url: '/create',
			templateUrl: 'app/person/person.form.html',
			controller: 'PersonEditController'
		}).state('app.person.edit',{
			url: '/edit/:id',
			templateUrl: 'app/person/person.form.html',
			controller: 'PersonEditController'
		}).state('app.person.view',{
			url: '/view/:id',
			templateUrl: 'app/person/person.view.html',
			controller: 'PersonViewController'
		});		
});