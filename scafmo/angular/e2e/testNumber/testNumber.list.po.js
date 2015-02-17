/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.doubleNrEl = element(by.model('search.doubleNr'));  
  this.floatNrEl = element(by.model('search.floatNr'));  
  this.floatNrScaleEl = element(by.model('search.floatNrScale'));  
  this.integerNrEl = element(by.model('search.integerNr'));  
  this.integerNrInListEl = element(by.model('search.integerNrInList'));  
  this.integerNrMaxEl = element(by.model('search.integerNrMax'));  
  this.integerNrMinEl = element(by.model('search.integerNrMin'));  
  this.integerNrNotEqualEl = element(by.model('search.integerNrNotEqual'));  
  this.integerNrRangeEl = element(by.model('search.integerNrRange'));  
  this.integerNrUniqueEl = element(by.model('search.integerNrUnique'));  
  this.longNrEl = element(by.model('search.longNr'));  
};

module.exports = new EditPage();

