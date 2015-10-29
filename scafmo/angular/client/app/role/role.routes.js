'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.role', {
		    url: '/role',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.role.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				'page@app.role': {
					templateUrl: 'app/role/role.list.html',
					controller: 'RoleListController'
				}
			}
		}).state('app.role.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.role.list'
			},
			views: {
				'page@app.role': {
					templateUrl: 'app/role/role.form.html',
					controller: 'RoleEditController'
				}
			},
			resolve:{
				roleData: function($stateParams, RoleService) {
					return new RoleService();
				}
			}
		}).state('app.role.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.role.list'
			},
			views: {
				'page@app.role': {
					templateUrl: 'app/role/role.view.html',
					controller: 'RoleViewController'
				}
			},
			resolve:{
				roleData: function($stateParams, RoleService){
					return RoleService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.role.view.edit',{
			url: '/edit',
			views: {
				'page@app.role': {
					templateUrl: 'app/role/role.form.html',
					controller: 'RoleEditController',
				}
			},
			resolve:{
				roleData: function($stateParams, RoleService){
					return RoleService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



		.state('app.role.view.userRole',{
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