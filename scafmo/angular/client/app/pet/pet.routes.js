'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.pet', {
		    url: '/pet',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.pet.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.pet.list.title" | translate}} '
			},
			views: {
				'page@app.pet': {
					templateUrl: 'app/pet/pet.list.html',
					controller: 'PetListController'
				}
			}
		}).state('app.pet.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.pet.list',
				label: '{{"pages.Pet.create.title" | translate}}'
			},
			views: {
				'page@app.pet': {
					templateUrl: 'app/pet/pet.form.html',
					controller: 'PetEditController'
				}
			},
			resolve:{
				petData: function($stateParams, PetService) {
					return new PetService();
				}
			}
		}).state('app.pet.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.pet.list',
				label: '{{"pages.pet.view.title" | translate}} '
			},
			views: {
				'page@app.pet': {
					templateUrl: 'app/pet/pet.view.html',
					controller: 'PetViewController'
				}
			},
			resolve:{
				petData: function($stateParams, PetService){
					return PetService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.pet.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.pet.view.edit.title" | translate}} '
            },
			views: {
				'page@app.pet': {
					templateUrl: 'app/pet/pet.form.html',
					controller: 'PetEditController',
				}
			},
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
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/petType/petType.view.modal.html',
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

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.pet.view.edit.petTypeSearchModal',{
		templateUrl: 'app/petType/petType.list.modal.html',
		controller: 'PetTypeListController'
	})
	
	.state('app.pet.view.ownerModal',{
		url: '/modal/owner/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/owner/owner.view.modal.html',
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

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.pet.view.edit.ownerSearchModal',{
		templateUrl: 'app/owner/owner.list.modal.html',
		controller: 'OwnerListController'
	})
	

		.state('app.pet.view.visit',{
			url: '/visit/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/visit/visit.list.html',
					controller: 'VisitListController'
				}
			}
		})
	
;
});