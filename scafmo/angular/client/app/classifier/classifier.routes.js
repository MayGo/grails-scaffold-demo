'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.classifier', {
		    url: '/classifier',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.classifier.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.classifier.list.title" | translate}} '
			},
			views: {
				'page@app.classifier': {
					templateUrl: 'app/classifier/classifier.list.html',
					controller: 'ClassifierListController'
				}
			}
		}).state('app.classifier.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.classifier.list',
				label: '{{"pages.Classifier.create.title" | translate}}'
			},
			views: {
				'page@app.classifier': {
					templateUrl: 'app/classifier/classifier.form.html',
					controller: 'ClassifierEditController'
				}
			},
			resolve:{
				classifierData: function($stateParams, ClassifierService) {
					return new ClassifierService();
				}
			}
		}).state('app.classifier.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.classifier.list',
				label: '{{"pages.classifier.view.title" | translate}} '
			},
			views: {
				'page@app.classifier': {
					templateUrl: 'app/classifier/classifier.view.html',
					controller: 'ClassifierViewController'
				}
			},
			resolve:{
				classifierData: function($stateParams, ClassifierService){
					return ClassifierService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.classifier.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.classifier.view.edit.title" | translate}} '
            },
			views: {
				'page@app.classifier': {
					templateUrl: 'app/classifier/classifier.form.html',
					controller: 'ClassifierEditController',
				}
			},
			resolve:{
				classifierData: function($stateParams, ClassifierService){
					return ClassifierService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});