/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.dateEl = element(by.model('search.date'));  
  this.descriptionEl = element(by.model('search.description'));  
  this.petEl = element(by.model('search.pets'));  
};

module.exports = new EditPage();

