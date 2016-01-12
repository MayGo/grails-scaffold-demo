'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.speciality', {
		    url: '/speciality',
			abstract: true,
		    template: '<div ui-view="page" class="fade-in-up"></div>'
		})
		.state('app.speciality.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			ncyBreadcrumb: {
				label: '{{"pages.speciality.list.title" | translate}} '
			},
			views: {
				'page@app.speciality': {
					templateUrl: 'app/speciality/speciality.list.html',
					controller: 'SpecialityListController'
				}
			}
		}).state('app.speciality.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.speciality.list',
				label: '{{"pages.Speciality.create.title" | translate}}'
			},
			views: {
				'page@app.speciality': {
					templateUrl: 'app/speciality/speciality.form.html',
					controller: 'SpecialityEditController'
				}
			},
			resolve:{
				specialityData: function($stateParams, SpecialityService) {
					return new SpecialityService();
				}
			}
		}).state('app.speciality.view',{
			url: '/view/:id',
			ncyBreadcrumb: {
				parent: 'app.speciality.list',
				label: '{{"pages.speciality.view.title" | translate}} '
			},
			views: {
				'page@app.speciality': {
					templateUrl: 'app/speciality/speciality.view.html',
					controller: 'SpecialityViewController'
				}
			},
			resolve:{
				specialityData: function($stateParams, SpecialityService){
					return SpecialityService.get({id:$stateParams.id}).$promise.then(
						function( response ){
							return response;
						}
					);
				}
			}
		}).state('app.speciality.view.edit',{
			url: '/edit',
            ncyBreadcrumb: {
                label: '{{"pages.speciality.view.edit.title" | translate}} '
            },
			views: {
				'page@app.speciality': {
					templateUrl: 'app/speciality/speciality.form.html',
					controller: 'SpecialityEditController',
				}
			},
			resolve:{
				specialityData: function($stateParams, SpecialityService){
					return SpecialityService.get({id:$stateParams.id}).$promise.then(
						function( response ){
								return response;
						}
					);
				}
			}
		})



;
});