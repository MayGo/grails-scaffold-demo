'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.person', {
		    url: '/person',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.person.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				"page@app.person": {
					templateUrl: 'app/person/person.list.html',
					controller: 'PersonListController'
				}
			}
		}).state('app.person.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.person.list'
			},
			views: {
				"page@app.person": {
					templateUrl: 'app/person/person.form.html',
					controller: 'PersonEditController'
				}
			},
			resolve:{
				personData: function($stateParams, PersonService) {
					return new PersonService();
				}
			}
		}).state('app.person.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.person.list'
			},
			views: {
				"page@app.person": {
					templateUrl: 'app/person/person.view.html',
					controller: 'PersonViewController'
				}
			},
			resolve:{
				personData: function($stateParams, PersonService){
					return PersonService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.person.view.edit',{
			url: '/edit',
			views: {
				"page@app.person": {
					templateUrl: 'app/person/person.form.html',
					controller: 'PersonEditController',
				}
			},
			resolve:{
				personData: function($stateParams, PersonService){
					return PersonService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});