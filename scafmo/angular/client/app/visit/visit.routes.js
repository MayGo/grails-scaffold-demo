'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.visit', {
		    url: '/visit',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.visit.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/visit/visit.list.html',
			controller: 'VisitListController'
		}).state('app.visit.create',{
			url: '/create',
			templateUrl: 'app/visit/visit.form.html',
			controller: 'VisitEditController',
			resolve:{
				visitData: function($stateParams, VisitService) {
					return new VisitService();
				}
			}
		}).state('app.visit.edit',{
			url: '/edit/:id',
			templateUrl: 'app/visit/visit.form.html',
			controller: 'VisitEditController',
			resolve:{
				visitData: function($stateParams, VisitService){
					return VisitService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.visit.view',{
			url: '/view/:id',
			templateUrl: 'app/visit/visit.view.html',
			controller: 'VisitViewController',
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
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/pet/pet.view.html',

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
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.visit.edit.petSearchModal',{
		templateUrl: 'app/pet/pet.list.html',
		controller: 'PetListController'
	})
	

;
});