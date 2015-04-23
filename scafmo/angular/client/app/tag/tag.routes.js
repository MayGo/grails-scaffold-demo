'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.tag', {
		    url: '/tag',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.tag.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/tag/tag.list.html',
			controller: 'TagListController'
		}).state('app.tag.create',{
			url: '/create',
			templateUrl: 'app/tag/tag.form.html',
			controller: 'TagEditController',
			resolve:{
				tagData: function($stateParams, TagService) {
					return new TagService();
				}
			}
		}).state('app.tag.edit',{
			url: '/edit/:id',
			templateUrl: 'app/tag/tag.form.html',
			controller: 'TagEditController',
			resolve:{
				tagData: function($stateParams, TagService){
					return TagService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.tag.view',{
			url: '/view/:id',
			templateUrl: 'app/tag/tag.view.html',
			controller: 'TagViewController',
				resolve:{
				tagData: function($stateParams, TagService){
					return TagService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		})



;
});