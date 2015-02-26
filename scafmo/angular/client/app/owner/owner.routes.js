'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.owner', {
		    url: '/owner',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.owner.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/owner/owner.list.html',
			controller: 'OwnerListController'
		}).state('app.owner.create',{
			url: '/create',
			templateUrl: 'app/owner/owner.form.html',
			controller: 'OwnerEditController'
		}).state('app.owner.edit',{
			url: '/edit/:id',
			templateUrl: 'app/owner/owner.form.html',
			controller: 'OwnerEditController'
		}).state('app.owner.view',{
			url: '/view/:id',
			templateUrl: 'app/owner/owner.view.html',
			controller: 'OwnerViewController'
		});		
});