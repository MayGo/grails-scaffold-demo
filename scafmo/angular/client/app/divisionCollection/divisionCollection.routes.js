'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollection', {
		    url: '/divisionCollection',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.divisionCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.divisionCollection.list.title" | translate}} '
			},
			views: {
				'page@app.divisionCollection': {
					templateUrl: 'app/divisionCollection/divisionCollection.list.html',
					controller: 'DivisionCollectionListController'
				}
			}
		}).state('app.divisionCollection.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.divisionCollection.list',
				label: '{{"pages.DivisionCollection.create.title" | translate}}'
			},
			views: {
				'page@app.divisionCollection': {
					templateUrl: 'app/divisionCollection/divisionCollection.form.html',
					controller: 'DivisionCollectionEditController'
				}
			},
			resolve:{
				divisionCollectionData: function($stateParams, DivisionCollectionService) {
					return new DivisionCollectionService();
				}
			}
		}).state('app.divisionCollection.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.divisionCollection.list',
				label: '{{"pages.divisionCollection.view.title" | translate}} '
			},
			views: {
				'page@app.divisionCollection': {
					templateUrl: 'app/divisionCollection/divisionCollection.view.html',
					controller: 'DivisionCollectionViewController'
				}
			},
			resolve:{
				divisionCollectionData: function($stateParams, DivisionCollectionService){
					return DivisionCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.divisionCollection.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.divisionCollection.view.edit.title" | translate}} '
            },
			views: {
				'page@app.divisionCollection': {
					templateUrl: 'app/divisionCollection/divisionCollection.form.html',
					controller: 'DivisionCollectionEditController',
				}
			},
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
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/divisionCollection/divisionCollection.view.modal.html',
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

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.divisionCollection.view.edit.divisionCollectionSearchModal',{
		templateUrl: 'app/divisionCollection/divisionCollection.list.modal.html',
		controller: 'DivisionCollectionListController'
	})
	

		.state('app.divisionCollection.view.divisionCollection',{
			url: '/divisionCollection/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/divisionCollection/divisionCollection.list.html',
					controller: 'DivisionCollectionListController'
				}
			}
		})
	
		.state('app.divisionCollection.view.personCollection',{
			url: '/personCollection/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/personCollection/personCollection.list.html',
					controller: 'PersonCollectionListController'
				}
			}
		})
	
;
});