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
			controller: 'PetEditController',
			resolve:{
				petData: function($stateParams, PetService) {
					return new PetService();
				}
			}
		}).state('app.pet.edit',{
			url: '/edit/:id',
			templateUrl: 'app/pet/pet.form.html',
			controller: 'PetEditController',
			resolve:{
				petData: function($stateParams, PetService){
					return PetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.pet.view',{
			url: '/view/:id',
			templateUrl: 'app/pet/pet.view.html',
			controller: 'PetViewController',
				resolve:{
				petData: function($stateParams, PetService){
					return PetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})


	.state('app.pet.view.petTypeModal',{
		url: '/modal/petType/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/petType/petType.view.html',

				resolve: {
					petTypeData: function($stateParams, PetTypeService){
						//TODO: Add parent ($stateParams.id) to query
						return PetTypeService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'PetTypeViewController',
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.pet.edit.petTypeSearchModal',{
		templateUrl: 'app/petType/petType.list.html',
		controller: 'PetTypeListController'
	})
	
	.state('app.pet.view.ownerModal',{
		url: '/modal/owner/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/owner/owner.view.html',

				resolve: {
					ownerData: function($stateParams, OwnerService){
						//TODO: Add parent ($stateParams.id) to query
						return OwnerService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'OwnerViewController',
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.pet.edit.ownerSearchModal',{
		templateUrl: 'app/owner/owner.list.html',
		controller: 'OwnerListController'
	})
	

		.state('app.pet.view.visit',{
			url: '/visit/:relationName',
			templateUrl: 'app/visit/visit.list.html',
			controller: 'VisitListController'
		})
	
;
});