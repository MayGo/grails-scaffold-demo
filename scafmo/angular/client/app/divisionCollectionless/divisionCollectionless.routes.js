'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.divisionCollectionless', {
		    url: '/divisionCollectionless',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.divisionCollectionless.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
			controller: 'DivisionCollectionlessListController'
		}).state('app.divisionCollectionless.create',{
			url: '/create',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
			controller: 'DivisionCollectionlessEditController',
			resolve:{
				divisionCollectionlessData: function($stateParams, DivisionCollectionlessService) {
					return new DivisionCollectionlessService();
				}
			}
		}).state('app.divisionCollectionless.edit',{
			url: '/edit/:id',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.form.html',
			controller: 'DivisionCollectionlessEditController',
			resolve:{
				divisionCollectionlessData: function($stateParams, DivisionCollectionlessService){
					return DivisionCollectionlessService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.divisionCollectionless.view',{
			url: '/view/:id',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.view.html',
			controller: 'DivisionCollectionlessViewController',
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
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/divisionCollectionless/divisionCollectionless.view.html',

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
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.divisionCollectionless.edit.divisionCollectionlessSearchModal',{
		templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
		controller: 'DivisionCollectionlessListController'
	})
	

		.state('app.divisionCollectionless.view.divisionCollectionless',{
			url: '/divisionCollectionless/:relationName',
			templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
			controller: 'DivisionCollectionlessListController'
		})
	
		.state('app.divisionCollectionless.view.personCollectionless',{
			url: '/personCollectionless/:relationName',
			templateUrl: 'app/personCollectionless/personCollectionless.list.html',
			controller: 'PersonCollectionlessListController'
		})
	
;
});