'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.role', {
		    url: '/role',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.role.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/role/role.list.html',
			controller: 'RoleListController'
		}).state('app.role.create',{
			url: '/create',
			templateUrl: 'app/role/role.form.html',
			controller: 'RoleEditController',
			resolve:{
				roleData: function($stateParams, RoleService) {
					return new RoleService();
				}
			}
		}).state('app.role.edit',{
			url: '/edit/:id',
			templateUrl: 'app/role/role.form.html',
			controller: 'RoleEditController',
			resolve:{
				roleData: function($stateParams, RoleService){
					return RoleService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.role.view',{
			url: '/view/:id',
			templateUrl: 'app/role/role.view.html',
			controller: 'RoleViewController',
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
			data:{
				isTab:true
			},
			templateUrl: 'app/userRole/userRole.list.html',
			controller: 'UserRoleListController'
		})
	
;
});