'use strict';

angular.module('angularDemoApp')
  .controller('ReloginController', function ($scope, relogin, $location, $translate, SessionService, appConfig) {

    if (relogin) {
      $translate('pages.session.messages.relogin').then(function (msg) {
        $scope.message = msg;
      });
    } else {
      $translate('pages.session.messages.logged-out').then(function (msg) {
        $scope.message = msg;
      });
    }

    $scope.login = function () {
      $location.path('/login');
    };

    $scope.casLogoutUrl = appConfig.loginUrl + '/logout?url=' + $location.absUrl();

  });
