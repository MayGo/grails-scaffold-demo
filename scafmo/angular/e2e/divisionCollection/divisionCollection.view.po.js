/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.nameEl = element(by.binding('divisionCollection.name'));  
  this.personsEl = element(by.binding('divisionCollection.persons'));  
  this.headDivisionEl = element(by.binding('divisionCollection.headDivision'));  
};

module.exports = new ViewPage();

