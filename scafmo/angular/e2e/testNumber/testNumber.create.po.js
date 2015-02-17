'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('testNumberSubmitBtn'));
  
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

module.exports = new CreatePage();

