/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.birthDateEl = element(by.model('search.birthDate'));  
  this.nameEl = element(by.model('search.name'));  
  this.visitsEl = element(by.model('search.visits'));  
  this.typeEl = element(by.model('search.types'));  
  this.ownerEl = element(by.model('search.owners'));  
};

module.exports = new EditPage();

