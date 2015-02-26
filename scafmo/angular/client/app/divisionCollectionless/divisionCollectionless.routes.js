'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollectionless', {
		    url: '/divisionCollectionless',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.divisionCollectionless.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
			controller: 'DivisionCollectionlessListController'
		}).state('app.divisionCollectionless.create',{
			url: '/create',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
			controller: 'DivisionCollectionlessEditController'
		}).state('app.divisionCollectionless.edit',{
			url: '/edit/:id',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
			controller: 'DivisionCollectionlessEditController'
		}).state('app.divisionCollectionless.view',{
			url: '/view/:id',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.view.html',
			controller: 'DivisionCollectionlessViewController'
		});		
});