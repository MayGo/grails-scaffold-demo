'use strict';

angular.module('angularDemoApp')
  .controller('LogoutController', function($scope, $location, $translate, SessionService, appConfig) {

	$translate('pages.session.messages.logging-out').then(function (msg) {
		$scope.message = msg;
	});

    if (!SessionService.isAuthenticated()) {
		$translate('pages.session.messages.already-logged-out').then(function (msg) {
			$scope.message = msg;
		});
    }
	$scope.casLogoutUrl = appConfig.loginUrl + '/logout?url=' + $location.absUrl();

    SessionService.logout().then(function() {
        console.log('Logged out');
        $translate('pages.session.messages.logged-out').then(function (msg) {
			$scope.message = msg;
		});
    }).catch(function (response) {
      console.error(response);
    });

  });
