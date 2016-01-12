'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.vet', {
		    url: '/vet',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.vet.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.vet.list.title" | translate}} '
			},
			views: {
				'page@app.vet': {
					templateUrl: 'app/vet/vet.list.html',
					controller: 'VetListController'
				}
			}
		}).state('app.vet.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.vet.list',
				label: '{{"pages.Vet.create.title" | translate}}'
			},
			views: {
				'page@app.vet': {
					templateUrl: 'app/vet/vet.form.html',
					controller: 'VetEditController'
				}
			},
			resolve:{
				vetData: function($stateParams, VetService) {
					return new VetService();
				}
			}
		}).state('app.vet.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.vet.list',
				label: '{{"pages.vet.view.title" | translate}} '
			},
			views: {
				'page@app.vet': {
					templateUrl: 'app/vet/vet.view.html',
					controller: 'VetViewController'
				}
			},
			resolve:{
				vetData: function($stateParams, VetService){
					return VetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.vet.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.vet.view.edit.title" | translate}} '
            },
			views: {
				'page@app.vet': {
					templateUrl: 'app/vet/vet.form.html',
					controller: 'VetEditController',
				}
			},
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