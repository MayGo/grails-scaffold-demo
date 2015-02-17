/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.accountExpiredEl = element(by.binding('user.accountExpired'));  
  this.accountLockedEl = element(by.binding('user.accountLocked'));  
  this.enabledEl = element(by.binding('user.enabled'));  
  this.passwordExpiredEl = element(by.binding('user.passwordExpired'));  
  this.usernameEl = element(by.binding('user.username'));  
};

module.exports = new ViewPage();

