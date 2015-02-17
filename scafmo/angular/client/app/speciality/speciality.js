'use strict';

angular.module('angularDemoApp')
	.config(function ($stateProvider) {
$stateProvider
		.state('app.speciality', {
		    url: '/speciality',
		    template: '<div ui-view class="fade-in-up"></div>'
		})
		.state('app.speciality.list', {
			url: '/list?search',//TODO: search so that search is not an object in url
			templateUrl: 'app/speciality/speciality.list.html',
			controller: 'SpecialityListController'
		}).state('app.speciality.create',{
			url: '/create',
			templateUrl: 'app/speciality/speciality.form.html',
			controller: 'SpecialityEditController'
		}).state('app.speciality.edit',{
			url: '/edit/:id',
			templateUrl: 'app/speciality/speciality.form.html',
			controller: 'SpecialityEditController'
		}).state('app.speciality.view',{
			url: '/view/:id',
			templateUrl: 'app/speciality/speciality.view.html',
			controller: 'SpecialityViewController'
		});		
});