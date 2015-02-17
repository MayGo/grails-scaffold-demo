/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.blankStrEl = element(by.binding('testString.blankStr'));  
  this.creditCardStrEl = element(by.binding('testString.creditCardStr'));  
  this.emailStrEl = element(by.binding('testString.emailStr'));  
  this.inListStrEl = element(by.binding('testString.inListStr'));  
  this.matchesStrEl = element(by.binding('testString.matchesStr'));  
  this.maxSizeStrEl = element(by.binding('testString.maxSizeStr'));  
  this.minSizeStrEl = element(by.binding('testString.minSizeStr'));  
  this.notEqualStrEl = element(by.binding('testString.notEqualStr'));  
  this.sizeStrEl = element(by.binding('testString.sizeStr'));  
  this.uniqueStrEl = element(by.binding('testString.uniqueStr'));  
  this.urlStrEl = element(by.binding('testString.urlStr'));  
};

module.exports = new ViewPage();

