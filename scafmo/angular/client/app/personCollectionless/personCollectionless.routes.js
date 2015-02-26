'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.personCollectionless', {
		    url: '/personCollectionless',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.personCollectionless.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/personCollectionless/personCollectionless.list.html',
			controller: 'PersonCollectionlessListController'
		}).state('app.personCollectionless.create',{
			url: '/create',
			templateUrl: 'app/personCollectionless/personCollectionless.form.html',
			controller: 'PersonCollectionlessEditController'
		}).state('app.personCollectionless.edit',{
			url: '/edit/:id',
			templateUrl: 'app/personCollectionless/personCollectionless.form.html',
			controller: 'PersonCollectionlessEditController'
		}).state('app.personCollectionless.view',{
			url: '/view/:id',
			templateUrl: 'app/personCollectionless/personCollectionless.view.html',
			controller: 'PersonCollectionlessViewController'
		});		
});