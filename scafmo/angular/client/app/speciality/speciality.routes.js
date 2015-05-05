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
			views: {
				"page@app.speciality": {
					templateUrl: 'app/speciality/speciality.list.html',
					controller: 'SpecialityListController'
				}
			}
		}).state('app.speciality.create',{
			url: '/create',
			ncyBreadcrumb: {
				parent: 'app.speciality.list'
			},
			views: {
				"page@app.speciality": {
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
				parent: 'app.speciality.list'
			},
			views: {
				"page@app.speciality": {
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
			views: {
				"page@app.speciality": {
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