/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.doubleNrEl = element(by.model('testNumber.doubleNr'));  
  this.floatNrEl = element(by.model('testNumber.floatNr'));  
  this.floatNrScaleEl = element(by.model('testNumber.floatNrScale'));  
  this.integerNrEl = element(by.model('testNumber.integerNr'));  
  this.integerNrInListEl = element(by.model('testNumber.integerNrInList'));  
  this.integerNrMaxEl = element(by.model('testNumber.integerNrMax'));  
  this.integerNrMinEl = element(by.model('testNumber.integerNrMin'));  
  this.integerNrNotEqualEl = element(by.model('testNumber.integerNrNotEqual'));  
  this.integerNrRangeEl = element(by.model('testNumber.integerNrRange'));  
  this.integerNrUniqueEl = element(by.model('testNumber.integerNrUnique'));  
  this.longNrEl = element(by.model('testNumber.longNr'));  
};

module.exports = new EditPage();

