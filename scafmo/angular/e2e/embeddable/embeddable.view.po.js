/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.jsonMapEl = element(by.binding('embeddable.jsonMap'));  
  this.myAcEl = element(by.binding('embeddable.myAc'));  
  this.strEl = element(by.binding('embeddable.str'));  
};

module.exports = new ViewPage();

