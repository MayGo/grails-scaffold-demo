/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.jsonMapEl = element(by.model('search.jsonMap'));  
  this.myAcEl = element(by.model('search.myAc'));  
  this.strEl = element(by.model('search.str'));  
};

module.exports = new EditPage();

