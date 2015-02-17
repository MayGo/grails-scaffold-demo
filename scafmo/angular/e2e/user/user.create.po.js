'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('userSubmitBtn'));
  
  this.accountExpiredEl = element(by.model('user.accountExpired'));  
  this.accountLockedEl = element(by.model('user.accountLocked'));  
  this.enabledEl = element(by.model('user.enabled'));  
  this.passwordExpiredEl = element(by.model('user.passwordExpired'));  
  this.usernameEl = element(by.model('user.username'));  
};

module.exports = new CreatePage();

