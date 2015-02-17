'use strict';

angular.module('angularDemoApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('login', {
        url: '/login',
        controller: 'LoginController'
      });
  });