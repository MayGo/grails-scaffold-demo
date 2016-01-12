'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.embeddable', {
		    url: '/embeddable',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.embeddable.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.embeddable.list.title" | translate}} '
			},
			views: {
				'page@app.embeddable': {
					templateUrl: 'app/embeddable/embeddable.list.html',
					controller: 'EmbeddableListController'
				}
			}
		}).state('app.embeddable.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.embeddable.list',
				label: '{{"pages.Embeddable.create.title" | translate}}'
			},
			views: {
				'page@app.embeddable': {
					templateUrl: 'app/embeddable/embeddable.form.html',
					controller: 'EmbeddableEditController'
				}
			},
			resolve:{
				embeddableData: function($stateParams, EmbeddableService) {
					return new EmbeddableService();
				}
			}
		}).state('app.embeddable.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.embeddable.list',
				label: '{{"pages.embeddable.view.title" | translate}} '
			},
			views: {
				'page@app.embeddable': {
					templateUrl: 'app/embeddable/embeddable.view.html',
					controller: 'EmbeddableViewController'
				}
			},
			resolve:{
				embeddableData: function($stateParams, EmbeddableService){
					return EmbeddableService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.embeddable.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.embeddable.view.edit.title" | translate}} '
            },
			views: {
				'page@app.embeddable': {
					templateUrl: 'app/embeddable/embeddable.form.html',
					controller: 'EmbeddableEditController',
				}
			},
			resolve:{
				embeddableData: function($stateParams, EmbeddableService){
					return EmbeddableService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.embeddable.view.embed',{
			url: '/embed/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/embed/embed.list.html',
					controller: 'EmbedListController'
				}
			}
		})
	
;
});