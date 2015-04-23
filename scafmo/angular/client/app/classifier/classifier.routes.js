'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.classifier', {
		    url: '/classifier',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.classifier.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/classifier/classifier.list.html',
			controller: 'ClassifierListController'
		}).state('app.classifier.create',{
			url: '/create',
			templateUrl: 'app/classifier/classifier.form.html',
			controller: 'ClassifierEditController',
			resolve:{
				classifierData: function($stateParams, ClassifierService) {
					return new ClassifierService();
				}
			}
		}).state('app.classifier.edit',{
			url: '/edit/:id',
			templateUrl: 'app/classifier/classifier.form.html',
			controller: 'ClassifierEditController',
			resolve:{
				classifierData: function($stateParams, ClassifierService){
					return ClassifierService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.classifier.view',{
			url: '/view/:id',
			templateUrl: 'app/classifier/classifier.view.html',
			controller: 'ClassifierViewController',
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