'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.embed', {
		    url: '/embed',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.embed.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.embed': {
					templateUrl: 'app/embed/embed.list.html',
					controller: 'EmbedListController'
				}
			}
		}).state('app.embed.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.embed.list'
			},
			views: {
				'page@app.embed': {
					templateUrl: 'app/embed/embed.form.html',
					controller: 'EmbedEditController'
				}
			},
			resolve:{
				embedData: function($stateParams, EmbedService) {
					return new EmbedService();
				}
			}
		}).state('app.embed.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.embed.list'
			},
			views: {
				'page@app.embed': {
					templateUrl: 'app/embed/embed.view.html',
					controller: 'EmbedViewController'
				}
			},
			resolve:{
				embedData: function($stateParams, EmbedService){
					return EmbedService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.embed.view.edit',{
			url: '/edit',
			views: {
				'page@app.embed': {
					templateUrl: 'app/embed/embed.form.html',
					controller: 'EmbedEditController',
				}
			},
			resolve:{
				embedData: function($stateParams, EmbedService){
					return EmbedService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});