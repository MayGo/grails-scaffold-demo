'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testNumber', {
		    url: '/testNumber',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.testNumber.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/testNumber/testNumber.list.html',
			controller: 'TestNumberListController'
		}).state('app.testNumber.create',{
			url: '/create',
			templateUrl: 'app/testNumber/testNumber.form.html',
			controller: 'TestNumberEditController',
			resolve:{
				testNumberData: function($stateParams, TestNumberService) {
					return new TestNumberService();
				}
			}
		}).state('app.testNumber.edit',{
			url: '/edit/:id',
			templateUrl: 'app/testNumber/testNumber.form.html',
			controller: 'TestNumberEditController',
			resolve:{
				testNumberData: function($stateParams, TestNumberService){
					return TestNumberService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.testNumber.view',{
			url: '/view/:id',
			templateUrl: 'app/testNumber/testNumber.view.html',
			controller: 'TestNumberViewController',
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