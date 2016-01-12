'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testString', {
		    url: '/testString',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.testString.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.testString.list.title" | translate}} '
			},
			views: {
				'page@app.testString': {
					templateUrl: 'app/testString/testString.list.html',
					controller: 'TestStringListController'
				}
			}
		}).state('app.testString.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.testString.list',
				label: '{{"pages.TestString.create.title" | translate}}'
			},
			views: {
				'page@app.testString': {
					templateUrl: 'app/testString/testString.form.html',
					controller: 'TestStringEditController'
				}
			},
			resolve:{
				testStringData: function($stateParams, TestStringService) {
					return new TestStringService();
				}
			}
		}).state('app.testString.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.testString.list',
				label: '{{"pages.testString.view.title" | translate}} '
			},
			views: {
				'page@app.testString': {
					templateUrl: 'app/testString/testString.view.html',
					controller: 'TestStringViewController'
				}
			},
			resolve:{
				testStringData: function($stateParams, TestStringService){
					return TestStringService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.testString.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.testString.view.edit.title" | translate}} '
            },
			views: {
				'page@app.testString': {
					templateUrl: 'app/testString/testString.form.html',
					controller: 'TestStringEditController',
				}
			},
			resolve:{
				testStringData: function($stateParams, TestStringService){
					return TestStringService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.testString.view.testOther',{
			url: '/testOther/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/testOther/testOther.list.html',
					controller: 'TestOtherListController'
				}
			}
		})
	
;
});