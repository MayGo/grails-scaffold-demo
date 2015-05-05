'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.owner', {
		    url: '/owner',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.owner.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				"page@app.owner": {
					templateUrl: 'app/owner/owner.list.html',
					controller: 'OwnerListController'
				}
			}
		}).state('app.owner.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.owner.list'
			},
			views: {
				"page@app.owner": {
					templateUrl: 'app/owner/owner.form.html',
					controller: 'OwnerEditController'
				}
			},
			resolve:{
				ownerData: function($stateParams, OwnerService) {
					return new OwnerService();
				}
			}
		}).state('app.owner.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.owner.list'
			},
			views: {
				"page@app.owner": {
					templateUrl: 'app/owner/owner.view.html',
					controller: 'OwnerViewController'
				}
			},
			resolve:{
				ownerData: function($stateParams, OwnerService){
					return OwnerService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.owner.view.edit',{
			url: '/edit',
			views: {
				"page@app.owner": {
					templateUrl: 'app/owner/owner.form.html',
					controller: 'OwnerEditController',
				}
			},
			resolve:{
				ownerData: function($stateParams, OwnerService){
					return OwnerService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.owner.view.pet',{
			url: '/pet/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				"tabs": {
					templateUrl: 'app/pet/pet.list.html',
					controller: 'PetListController'
				}
			}
		})
	
;
});