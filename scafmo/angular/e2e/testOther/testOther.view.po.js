/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.booleanNullableEl = element(by.binding('testOther.booleanNullable'));  
  this.testDateEl = element(by.binding('testOther.testDate'));  
  this.testEnumEl = element(by.binding('testOther.testEnum'));  
  this.testStringTypeEl = element(by.binding('testOther.testStringType'));  
};

module.exports = new ViewPage();

