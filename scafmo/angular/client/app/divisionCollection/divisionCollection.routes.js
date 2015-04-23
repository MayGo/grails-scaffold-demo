'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollection', {
		    url: '/divisionCollection',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.divisionCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/divisionCollection/divisionCollection.list.html',
			controller: 'DivisionCollectionListController'
		}).state('app.divisionCollection.create',{
			url: '/create',
			templateUrl: 'app/divisionCollection/divisionCollection.form.html',
			controller: 'DivisionCollectionEditController',
			resolve:{
				divisionCollectionData: function($stateParams, DivisionCollectionService) {
					return new DivisionCollectionService();
				}
			}
		}).state('app.divisionCollection.edit',{
			url: '/edit/:id',
			templateUrl: 'app/divisionCollection/divisionCollection.form.html',
			controller: 'DivisionCollectionEditController',
			resolve:{
				divisionCollectionData: function($stateParams, DivisionCollectionService){
					return DivisionCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.divisionCollection.view',{
			url: '/view/:id',
			templateUrl: 'app/divisionCollection/divisionCollection.view.html',
			controller: 'DivisionCollectionViewController',
				resolve:{
				divisionCollectionData: function($stateParams, DivisionCollectionService){
					return DivisionCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})


	.state('app.divisionCollection.view.divisionCollectionModal',{
		url: '/modal/divisionCollection/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/divisionCollection/divisionCollection.view.html',

				resolve: {
					divisionCollectionData: function($stateParams, DivisionCollectionService){
						//TODO: Add parent ($stateParams.id) to query
						return DivisionCollectionService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'DivisionCollectionViewController',
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.divisionCollection.edit.divisionCollectionSearchModal',{
		templateUrl: 'app/divisionCollection/divisionCollection.list.html',
		controller: 'DivisionCollectionListController'
	})
	

		.state('app.divisionCollection.view.divisionCollection',{
			url: '/divisionCollection/:relationName',
			templateUrl: 'app/divisionCollection/divisionCollection.list.html',
			controller: 'DivisionCollectionListController'
		})
	
		.state('app.divisionCollection.view.personCollection',{
			url: '/personCollection/:relationName',
			templateUrl: 'app/personCollection/personCollection.list.html',
			controller: 'PersonCollectionListController'
		})
	
;
});