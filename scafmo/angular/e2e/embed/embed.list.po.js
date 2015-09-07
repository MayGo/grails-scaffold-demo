/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.acCustomMapEl = element(by.model('search.acCustomMap'));  
  this.acMapEl = element(by.model('search.acMap'));  
  this.acStrEl = element(by.model('search.acStr'));  
  this.muFileLocationEl = element(by.model('search.muFileLocation'));  
  this.myFileEl = element(by.model('search.myFile'));  
};

module.exports = new EditPage();

