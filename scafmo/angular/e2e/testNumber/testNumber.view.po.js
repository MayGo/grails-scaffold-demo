/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.doubleNrEl = element(by.binding('testNumber.doubleNr'));  
  this.floatNrEl = element(by.binding('testNumber.floatNr'));  
  this.floatNrScaleEl = element(by.binding('testNumber.floatNrScale'));  
  this.integerNrEl = element(by.binding('testNumber.integerNr'));  
  this.integerNrInListEl = element(by.binding('testNumber.integerNrInList'));  
  this.integerNrMaxEl = element(by.binding('testNumber.integerNrMax'));  
  this.integerNrMinEl = element(by.binding('testNumber.integerNrMin'));  
  this.integerNrNotEqualEl = element(by.binding('testNumber.integerNrNotEqual'));  
  this.integerNrRangeEl = element(by.binding('testNumber.integerNrRange'));  
  this.integerNrUniqueEl = element(by.binding('testNumber.integerNrUnique'));  
  this.longNrEl = element(by.binding('testNumber.longNr'));  
};

module.exports = new ViewPage();

