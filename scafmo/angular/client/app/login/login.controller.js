'use strict';

angular.module('angularDemoApp')
	.controller('LoginController', function ($scope, SessionService, $location) {
		SessionService.login()
			.then(function () {
				$location.path('/');
			})
			.catch(function (response) {
				console.error(response);
			});

	});
