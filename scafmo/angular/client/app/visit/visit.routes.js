'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.visit', {
		    url: '/visit',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.visit.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.visit.list.title" | translate}} '
			},
			views: {
				'page@app.visit': {
					templateUrl: 'app/visit/visit.list.html',
					controller: 'VisitListController'
				}
			}
		}).state('app.visit.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.visit.list',
				label: '{{"pages.Visit.create.title" | translate}}'
			},
			views: {
				'page@app.visit': {
					templateUrl: 'app/visit/visit.form.html',
					controller: 'VisitEditController'
				}
			},
			resolve:{
				visitData: function($stateParams, VisitService) {
					return new VisitService();
				}
			}
		}).state('app.visit.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.visit.list',
				label: '{{"pages.visit.view.title" | translate}} '
			},
			views: {
				'page@app.visit': {
					templateUrl: 'app/visit/visit.view.html',
					controller: 'VisitViewController'
				}
			},
			resolve:{
				visitData: function($stateParams, VisitService){
					return VisitService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.visit.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.visit.view.edit.title" | translate}} '
            },
			views: {
				'page@app.visit': {
					templateUrl: 'app/visit/visit.form.html',
					controller: 'VisitEditController',
				}
			},
			resolve:{
				visitData: function($stateParams, VisitService){
					return VisitService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})


	.state('app.visit.view.petModal',{
		url: '/modal/pet/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/pet/pet.view.modal.html',
				resolve: {
					petData: function($stateParams, PetService){
						//TODO: Add parent ($stateParams.id) to query
						return PetService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'PetViewController',

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.visit.view.edit.petSearchModal',{
		templateUrl: 'app/pet/pet.list.modal.html',
		controller: 'PetListController'
	})
	

;
});