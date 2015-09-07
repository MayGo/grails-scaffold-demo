/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.blankStrEl = element(by.model('search.blankStr'));  
  this.creditCardStrEl = element(by.model('search.creditCardStr'));  
  this.emailStrEl = element(by.model('search.emailStr'));  
  this.inListStrEl = element(by.model('search.inListStr'));  
  this.matchesStrEl = element(by.model('search.matchesStr'));  
  this.maxSizeStrEl = element(by.model('search.maxSizeStr'));  
  this.minSizeStrEl = element(by.model('search.minSizeStr'));  
  this.notEqualStrEl = element(by.model('search.notEqualStr'));  
  this.sizeStrEl = element(by.model('search.sizeStr'));  
  this.textareaStrEl = element(by.model('search.textareaStr'));  
  this.uniqueStrEl = element(by.model('search.uniqueStr'));  
  this.urlStrEl = element(by.model('search.urlStr'));  
};

module.exports = new EditPage();

