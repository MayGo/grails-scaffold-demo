'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testNumber', {
		    url: '/testNumber',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.testNumber.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.testNumber.list.title" | translate}} '
			},
			views: {
				'page@app.testNumber': {
					templateUrl: 'app/testNumber/testNumber.list.html',
					controller: 'TestNumberListController'
				}
			}
		}).state('app.testNumber.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.testNumber.list',
				label: '{{"pages.TestNumber.create.title" | translate}}'
			},
			views: {
				'page@app.testNumber': {
					templateUrl: 'app/testNumber/testNumber.form.html',
					controller: 'TestNumberEditController'
				}
			},
			resolve:{
				testNumberData: function($stateParams, TestNumberService) {
					return new TestNumberService();
				}
			}
		}).state('app.testNumber.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.testNumber.list',
				label: '{{"pages.testNumber.view.title" | translate}} '
			},
			views: {
				'page@app.testNumber': {
					templateUrl: 'app/testNumber/testNumber.view.html',
					controller: 'TestNumberViewController'
				}
			},
			resolve:{
				testNumberData: function($stateParams, TestNumberService){
					return TestNumberService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.testNumber.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.testNumber.view.edit.title" | translate}} '
            },
			views: {
				'page@app.testNumber': {
					templateUrl: 'app/testNumber/testNumber.form.html',
					controller: 'TestNumberEditController',
				}
			},
			resolve:{
				testNumberData: function($stateParams, TestNumberService){
					return TestNumberService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});