'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.userRole', {
		    url: '/userRole',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.userRole.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			views: {
				"page@app.userRole": {
					templateUrl: 'app/userRole/userRole.list.html',
					controller: 'UserRoleListController'
				}
			}
		}).state('app.userRole.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.userRole.list'
			},
			views: {
				"page@app.userRole": {
					templateUrl: 'app/userRole/userRole.form.html',
					controller: 'UserRoleEditController'
				}
			},
			resolve:{
				userRoleData: function($stateParams, UserRoleService) {
					return new UserRoleService();
				}
			}
		}).state('app.userRole.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.userRole.list'
			},
			views: {
				"page@app.userRole": {
					templateUrl: 'app/userRole/userRole.view.html',
					controller: 'UserRoleViewController'
				}
			},
			resolve:{
				userRoleData: function($stateParams, UserRoleService){
					return UserRoleService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.userRole.view.edit',{
			url: '/edit',
			views: {
				"page@app.userRole": {
					templateUrl: 'app/userRole/userRole.form.html',
					controller: 'UserRoleEditController',
				}
			},
			resolve:{
				userRoleData: function($stateParams, UserRoleService){
					return UserRoleService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})


	.state('app.userRole.view.roleModal',{
		url: '/modal/role/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/role/role.view.html',

				resolve: {
					roleData: function($stateParams, RoleService){
						//TODO: Add parent ($stateParams.id) to query
						return RoleService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'RoleViewController',
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.userRole.view.edit.roleSearchModal',{
		templateUrl: 'app/role/role.list.html',
		controller: 'RoleListController'
	})
	
	.state('app.userRole.view.userModal',{
		url: '/modal/user/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $modal, $resource) {
			var modalId = $stateParams.modalId;

			$modal.open({
				size:'lg',
				templateUrl: 'app/user/user.view.html',

				resolve: {
					userData: function($stateParams, UserService){
						//TODO: Add parent ($stateParams.id) to query
						return UserService.get({id:modalId}).$promise.then(
							function( response ){
								return response;
							}
						);
					}
				},
				controller: 'UserViewController',
			}).result.finally(function(item) {
				$state.go('^');
			});

		}

	}).state('app.userRole.view.edit.userSearchModal',{
		templateUrl: 'app/user/user.list.html',
		controller: 'UserListController'
	})
	

;
});