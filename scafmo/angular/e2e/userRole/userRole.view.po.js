/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.roleEl = element(by.binding('userRole.role'));  
  this.userEl = element(by.binding('userRole.user'));  
};

module.exports = new ViewPage();

