'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.personCollectionless', {
		    url: '/personCollectionless',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.personCollectionless.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/personCollectionless/personCollectionless.list.html',
			controller: 'PersonCollectionlessListController'
		}).state('app.personCollectionless.create',{
			url: '/create',
			templateUrl: 'app/personCollectionless/personCollectionless.form.html',
			controller: 'PersonCollectionlessEditController',
			resolve:{
				personCollectionlessData: function($stateParams, PersonCollectionlessService) {
					return new PersonCollectionlessService();
				}
			}
		}).state('app.personCollectionless.edit',{
			url: '/edit/:id',
			templateUrl: 'app/personCollectionless/personCollectionless.form.html',
			controller: 'PersonCollectionlessEditController',
			resolve:{
				personCollectionlessData: function($stateParams, PersonCollectionlessService){
					return PersonCollectionlessService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.personCollectionless.view',{
			url: '/view/:id',
			templateUrl: 'app/personCollectionless/personCollectionless.view.html',
			controller: 'PersonCollectionlessViewController',
				resolve:{
				personCollectionlessData: function($stateParams, PersonCollectionlessService){
					return PersonCollectionlessService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})


	.state('app.personCollectionless.view.divisionCollectionlessModal',{
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

	}).state('app.personCollectionless.edit.divisionCollectionlessSearchModal',{
		templateUrl: 'app/divisionCollectionless/divisionCollectionless.list.html',
		controller: 'DivisionCollectionlessListController'
	})
	

;
});