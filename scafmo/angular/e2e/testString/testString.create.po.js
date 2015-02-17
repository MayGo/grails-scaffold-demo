'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('testStringSubmitBtn'));
  
  this.blankStrEl = element(by.model('testString.blankStr'));  
  this.creditCardStrEl = element(by.model('testString.creditCardStr'));  
  this.emailStrEl = element(by.model('testString.emailStr'));  
  this.inListStrEl = element(by.model('testString.inListStr'));  
  this.matchesStrEl = element(by.model('testString.matchesStr'));  
  this.maxSizeStrEl = element(by.model('testString.maxSizeStr'));  
  this.minSizeStrEl = element(by.model('testString.minSizeStr'));  
  this.notEqualStrEl = element(by.model('testString.notEqualStr'));  
  this.sizeStrEl = element(by.model('testString.sizeStr'));  
  this.uniqueStrEl = element(by.model('testString.uniqueStr'));  
  this.urlStrEl = element(by.model('testString.urlStr'));  
};

module.exports = new CreatePage();

