/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.ageEl = element(by.binding('personCollection.age'));  
  this.nameEl = element(by.binding('personCollection.name'));  
  this.divisionEl = element(by.binding('personCollection.division'));  
};

module.exports = new ViewPage();

