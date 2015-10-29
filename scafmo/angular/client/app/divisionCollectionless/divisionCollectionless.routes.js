'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollectionless', {
		    url: '/divisionCollectionless',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.divisionCollectionless.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.divisionCollectionless': {
					templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
					controller: 'DivisionCollectionlessListController'
				}
			}
		}).state('app.divisionCollectionless.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.divisionCollectionless.list'
			},
			views: {
				'page@app.divisionCollectionless': {
					templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
					controller: 'DivisionCollectionlessEditController'
				}
			},
			resolve:{
				divisionCollectionlessData: function($stateParams, DivisionCollectionlessService) {
					return new DivisionCollectionlessService();
				}
			}
		}).state('app.divisionCollectionless.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.divisionCollectionless.list'
			},
			views: {
				'page@app.divisionCollectionless': {
					templateUrl: 'app/divisionCollectionless/divisionCollectionless.view.html',
					controller: 'DivisionCollectionlessViewController'
				}
			},
			resolve:{
				divisionCollectionlessData: function($stateParams, DivisionCollectionlessService){
					return DivisionCollectionlessService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.divisionCollectionless.view.edit',{
			url: '/edit',
			views: {
				'page@app.divisionCollectionless': {
					templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
					controller: 'DivisionCollectionlessEditController',
				}
			},
			resolve:{
				divisionCollectionlessData: function($stateParams, DivisionCollectionlessService){
					return DivisionCollectionlessService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})


	.state('app.divisionCollectionless.view.divisionCollectionlessModal',{
		url: '/modal/divisionCollectionless/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/divisionCollectionless/divisionCollectionless.view.modal.html',
				resolve: {
					divisionCollectionlessData: function($stateParams, DivisionCollectionlessService){
						//TODO: Add parent ($stateParams.id) to query
						return DivisionCollectionlessService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'DivisionCollectionlessViewController',

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.divisionCollectionless.view.edit.divisionCollectionlessSearchModal',{
		templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.modal.html',
		controller: 'DivisionCollectionlessListController'
	})
	

		.state('app.divisionCollectionless.view.divisionCollectionless',{
			url: '/divisionCollectionless/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
					controller: 'DivisionCollectionlessListController'
				}
			}
		})
	
		.state('app.divisionCollectionless.view.personCollectionless',{
			url: '/personCollectionless/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/personCollectionless/personCollectionless.list.html',
					controller: 'PersonCollectionlessListController'
				}
			}
		})
	
;
});