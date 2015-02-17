'use strict';

angular.module('angularDemoApp')
  .controller('ReloginController', function($scope, $location, $translate, SessionService, appConfig) {
	$translate('pages.session.messages.relogin').then(function (msg) {
		$scope.message = msg;
	});

    $scope.login = function() {
      $location.path("/login");
    };
	  $scope.casLogoutUrl = appConfig.loginUrl + '/logout?url=' + $location.absUrl();

  });
