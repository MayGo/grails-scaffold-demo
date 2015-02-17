/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.booleanNullableEl = element(by.model('search.booleanNullable'));  
  this.testDateEl = element(by.model('search.testDate'));  
  this.testEnumEl = element(by.model('search.testEnum'));  
  this.testStringTypeEl = element(by.model('search.testStringType'));  
};

module.exports = new EditPage();

