'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.personCollection', {
		    url: '/personCollection',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.personCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/personCollection/personCollection.list.html',
			controller: 'PersonCollectionListController'
		}).state('app.personCollection.create',{
			url: '/create',
			templateUrl: 'app/personCollection/personCollection.form.html',
			controller: 'PersonCollectionEditController'
		}).state('app.personCollection.edit',{
			url: '/edit/:id',
			templateUrl: 'app/personCollection/personCollection.form.html',
			controller: 'PersonCollectionEditController'
		}).state('app.personCollection.view',{
			url: '/view/:id',
			templateUrl: 'app/personCollection/personCollection.view.html',
			controller: 'PersonCollectionViewController'
		});		
});