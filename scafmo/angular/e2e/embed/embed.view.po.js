/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.acCustomMapEl = element(by.binding('embed.acCustomMap'));  
  this.acMapEl = element(by.binding('embed.acMap'));  
  this.acStrEl = element(by.binding('embed.acStr'));  
  this.muFileLocationEl = element(by.binding('embed.muFileLocation'));  
  this.myFileEl = element(by.binding('embed.myFile'));  
};

module.exports = new ViewPage();

