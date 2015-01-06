'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.pet', {
		    url: '/pet',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.pet.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/pet/pet.list.html',
			controller: 'PetListController'
		}).state('app.pet.create',{
			url: '/create',
			templateUrl: 'app/pet/pet.form.html',
			controller: 'PetEditController'
		}).state('app.pet.edit',{
			url: '/edit/:id',
			templateUrl: 'app/pet/pet.form.html',
			controller: 'PetEditController'
		}).state('app.pet.view',{
			url: '/view/:id',
			templateUrl: 'app/pet/pet.view.html',
			controller: 'PetViewController'
		});		
});