'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.vet', {
		    url: '/vet',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.vet.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/vet/vet.list.html',
			controller: 'VetListController'
		}).state('app.vet.create',{
			url: '/create',
			templateUrl: 'app/vet/vet.form.html',
			controller: 'VetEditController',
			resolve:{
				vetData: function($stateParams, VetService) {
					return new VetService();
				}
			}
		}).state('app.vet.edit',{
			url: '/edit/:id',
			templateUrl: 'app/vet/vet.form.html',
			controller: 'VetEditController',
			resolve:{
				vetData: function($stateParams, VetService){
					return VetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.vet.view',{
			url: '/view/:id',
			templateUrl: 'app/vet/vet.view.html',
			controller: 'VetViewController',
				resolve:{
				vetData: function($stateParams, VetService){
					return VetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})



;
});