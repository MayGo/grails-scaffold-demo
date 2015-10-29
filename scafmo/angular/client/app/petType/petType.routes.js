'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.petType', {
		    url: '/petType',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.petType.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.petType': {
					templateUrl: 'app/petType/petType.list.html',
					controller: 'PetTypeListController'
				}
			}
		}).state('app.petType.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.petType.list'
			},
			views: {
				'page@app.petType': {
					templateUrl: 'app/petType/petType.form.html',
					controller: 'PetTypeEditController'
				}
			},
			resolve:{
				petTypeData: function($stateParams, PetTypeService) {
					return new PetTypeService();
				}
			}
		}).state('app.petType.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.petType.list'
			},
			views: {
				'page@app.petType': {
					templateUrl: 'app/petType/petType.view.html',
					controller: 'PetTypeViewController'
				}
			},
			resolve:{
				petTypeData: function($stateParams, PetTypeService){
					return PetTypeService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.petType.view.edit',{
			url: '/edit',
			views: {
				'page@app.petType': {
					templateUrl: 'app/petType/petType.form.html',
					controller: 'PetTypeEditController',
				}
			},
			resolve:{
				petTypeData: function($stateParams, PetTypeService){
					return PetTypeService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.petType.view.pet',{
			url: '/pet/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/pet/pet.list.html',
					controller: 'PetListController'
				}
			}
		})
	
;
});