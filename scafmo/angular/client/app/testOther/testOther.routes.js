'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.testOther', {
		    url: '/testOther',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.testOther.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.testOther.list.title" | translate}} '
			},
			views: {
				'page@app.testOther': {
					templateUrl: 'app/testOther/testOther.list.html',
					controller: 'TestOtherListController'
				}
			}
		}).state('app.testOther.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.testOther.list',
				label: '{{"pages.TestOther.create.title" | translate}}'
			},
			views: {
				'page@app.testOther': {
					templateUrl: 'app/testOther/testOther.form.html',
					controller: 'TestOtherEditController'
				}
			},
			resolve:{
				testOtherData: function($stateParams, TestOtherService) {
					return new TestOtherService();
				}
			}
		}).state('app.testOther.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.testOther.list',
				label: '{{"pages.testOther.view.title" | translate}} '
			},
			views: {
				'page@app.testOther': {
					templateUrl: 'app/testOther/testOther.view.html',
					controller: 'TestOtherViewController'
				}
			},
			resolve:{
				testOtherData: function($stateParams, TestOtherService){
					return TestOtherService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.testOther.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.testOther.view.edit.title" | translate}} '
            },
			views: {
				'page@app.testOther': {
					templateUrl: 'app/testOther/testOther.form.html',
					controller: 'TestOtherEditController',
				}
			},
			resolve:{
				testOtherData: function($stateParams, TestOtherService){
					return TestOtherService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})


	.state('app.testOther.view.testStringModal',{
		url: '/modal/testString/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/testString/testString.view.modal.html',
				resolve: {
					testStringData: function($stateParams, TestStringService){
						//TODO: Add parent ($stateParams.id) to query
						return TestStringService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'TestStringViewController',

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.testOther.view.edit.testStringSearchModal',{
		templateUrl: 'app/testString/testString.list.modal.html',
		controller: 'TestStringListController'
	})
	

;
});