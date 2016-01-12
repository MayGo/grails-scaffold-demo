'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.user', {
		    url: '/user',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.user.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.user.list.title" | translate}} '
			},
			views: {
				'page@app.user': {
					templateUrl: 'app/user/user.list.html',
					controller: 'UserListController'
				}
			}
		}).state('app.user.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.user.list',
				label: '{{"pages.User.create.title" | translate}}'
			},
			views: {
				'page@app.user': {
					templateUrl: 'app/user/user.form.html',
					controller: 'UserEditController'
				}
			},
			resolve:{
				userData: function($stateParams, UserService) {
					return new UserService();
				}
			}
		}).state('app.user.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.user.list',
				label: '{{"pages.user.view.title" | translate}} '
			},
			views: {
				'page@app.user': {
					templateUrl: 'app/user/user.view.html',
					controller: 'UserViewController'
				}
			},
			resolve:{
				userData: function($stateParams, UserService){
					return UserService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.user.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.user.view.edit.title" | translate}} '
            },
			views: {
				'page@app.user': {
					templateUrl: 'app/user/user.form.html',
					controller: 'UserEditController',
				}
			},
			resolve:{
				userData: function($stateParams, UserService){
					return UserService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.user.view.userRole',{
			url: '/userRole/:relationName',
			ncyBreadcrumb: {
				skip: true
			},
			data:{
				isTab:true
			},
			views: {
				'tabs': {
					templateUrl: 'app/userRole/userRole.list.html',
					controller: 'UserRoleListController'
				}
			}
		})
	
;
});