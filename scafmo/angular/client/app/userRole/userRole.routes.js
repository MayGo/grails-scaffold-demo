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
			ncyBreadcrumb: {
				label: '{{"pages.userRole.list.title" | translate}} '
			},
			views: {
				'page@app.userRole': {
					templateUrl: 'app/userRole/userRole.list.html',
					controller: 'UserRoleListController'
				}
			}
		}).state('app.userRole.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.userRole.list',
				label: '{{"pages.UserRole.create.title" | translate}}'
			},
			views: {
				'page@app.userRole': {
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
				parent: 'app.userRole.list',
				label: '{{"pages.userRole.view.title" | translate}} '
			},
			views: {
				'page@app.userRole': {
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
            ncyBreadcrumb: {
                label: '{{"pages.userRole.view.edit.title" | translate}} '
            },
			views: {
				'page@app.userRole': {
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
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/role/role.view.modal.html',
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

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.userRole.view.edit.roleSearchModal',{
		templateUrl: 'app/role/role.list.modal.html',
		controller: 'RoleListController'
	})
	
	.state('app.userRole.view.userModal',{
		url: '/modal/user/:modalId',
		data:{
			isModal:true
		},
		onEnter: function($stateParams, $state, $mdDialog) {
			var modalId = $stateParams.modalId;
			$mdDialog.show({
				templateUrl: 'app/user/user.view.modal.html',
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

			}).then(function () {
				$state.go('^');
			});
		}

	}).state('app.userRole.view.edit.userSearchModal',{
		templateUrl: 'app/user/user.list.modal.html',
		controller: 'UserListController'
	})
	

;
});