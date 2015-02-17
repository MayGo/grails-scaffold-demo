'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('testOtherSubmitBtn'));
  
  this.booleanNullableEl = element(by.model('testOther.booleanNullable'));  
  this.testDateEl = element(by.model('testOther.testDate'));  
  this.testEnumEl = element(by.model('testOther.testEnum'));  
  this.testStringTypeEl = element(by.model('testOther.testStringType'));  
};

module.exports = new CreatePage();

