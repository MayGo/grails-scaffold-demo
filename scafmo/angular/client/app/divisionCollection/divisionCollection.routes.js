'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollection', {
		    url: '/divisionCollection',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.divisionCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/divisionCollection/divisionCollection.list.html',
			controller: 'DivisionCollectionListController'
		}).state('app.divisionCollection.create',{
			url: '/create',
			templateUrl: 'app/divisionCollection/divisionCollection.form.html',
			controller: 'DivisionCollectionEditController'
		}).state('app.divisionCollection.edit',{
			url: '/edit/:id',
			templateUrl: 'app/divisionCollection/divisionCollection.form.html',
			controller: 'DivisionCollectionEditController'
		}).state('app.divisionCollection.view',{
			url: '/view/:id',
			templateUrl: 'app/divisionCollection/divisionCollection.view.html',
			controller: 'DivisionCollectionViewController'
		});		
});