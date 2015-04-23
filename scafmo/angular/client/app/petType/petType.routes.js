'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.petType', {
		    url: '/petType',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.petType.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/petType/petType.list.html',
			controller: 'PetTypeListController'
		}).state('app.petType.create',{
			url: '/create',
			templateUrl: 'app/petType/petType.form.html',
			controller: 'PetTypeEditController',
			resolve:{
				petTypeData: function($stateParams, PetTypeService) {
					return new PetTypeService();
				}
			}
		}).state('app.petType.edit',{
			url: '/edit/:id',
			templateUrl: 'app/petType/petType.form.html',
			controller: 'PetTypeEditController',
			resolve:{
				petTypeData: function($stateParams, PetTypeService){
					return PetTypeService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.petType.view',{
			url: '/view/:id',
			templateUrl: 'app/petType/petType.view.html',
			controller: 'PetTypeViewController',
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
			templateUrl: 'app/pet/pet.list.html',
			controller: 'PetListController'
		})
	
;
});