'use strict';

angular.module('angularDemoApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('logout', {
        url: '/logout',
        templateUrl: 'app/logout/logout.html',
        controller: 'LogoutController'
      });
  });
