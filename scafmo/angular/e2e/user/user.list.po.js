/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.accountExpiredEl = element(by.model('search.accountExpired'));  
  this.accountLockedEl = element(by.model('search.accountLocked'));  
  this.enabledEl = element(by.model('search.enabled'));  
  this.passwordExpiredEl = element(by.model('search.passwordExpired'));  
  this.usernameEl = element(by.model('search.username'));  
};

module.exports = new EditPage();

