'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.personCollection', {
		    url: '/personCollection',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.personCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/personCollection/personCollection.list.html',
			controller: 'PersonCollectionListController'
		}).state('app.personCollection.create',{
			url: '/create',
			templateUrl: 'app/personCollection/personCollection.form.html',
			controller: 'PersonCollectionEditController',
			resolve:{
				personCollectionData: function($stateParams, PersonCollectionService) {
					return new PersonCollectionService();
				}
			}
		}).state('app.personCollection.edit',{
			url: '/edit/:id',
			templateUrl: 'app/personCollection/personCollection.form.html',
			controller: 'PersonCollectionEditController',
			resolve:{
				personCollectionData: function($stateParams, PersonCollectionService){
					return PersonCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.personCollection.view',{
			url: '/view/:id',
			templateUrl: 'app/personCollection/personCollection.view.html',
			controller: 'PersonCollectionViewController',
				resolve:{
				personCollectionData: function($stateParams, PersonCollectionService){
					return PersonCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})


	.state('app.personCollection.view.divisionCollectionModal',{
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

	}).state('app.personCollection.edit.divisionCollectionSearchModal',{
		templateUrl: 'app/divisionCollection/divisionCollection.list.html',
		controller: 'DivisionCollectionListController'
	})
	

;
});