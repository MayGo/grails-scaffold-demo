'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.personCollection', {
		    url: '/personCollection',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.personCollection.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.personCollection': {
					templateUrl: 'app/personCollection/personCollection.list.html',
					controller: 'PersonCollectionListController'
				}
			}
		}).state('app.personCollection.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.personCollection.list'
			},
			views: {
				'page@app.personCollection': {
					templateUrl: 'app/personCollection/personCollection.form.html',
					controller: 'PersonCollectionEditController'
				}
			},
			resolve:{
				personCollectionData: function($stateParams, PersonCollectionService) {
					return new PersonCollectionService();
				}
			}
		}).state('app.personCollection.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.personCollection.list'
			},
			views: {
				'page@app.personCollection': {
					templateUrl: 'app/personCollection/personCollection.view.html',
					controller: 'PersonCollectionViewController'
				}
			},
			resolve:{
				personCollectionData: function($stateParams, PersonCollectionService){
					return PersonCollectionService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.personCollection.view.edit',{
			url: '/edit',
			views: {
				'page@app.personCollection': {
					templateUrl: 'app/personCollection/personCollection.form.html',
					controller: 'PersonCollectionEditController',
				}
			},
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

	}).state('app.personCollection.view.edit.divisionCollectionSearchModal',{
		templateUrl: 'app/divisionCollection/divisionCollection.list.modal.html',
		controller: 'DivisionCollectionListController'
	})
	

;
});