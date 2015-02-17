/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.firstNameEl = element(by.binding('vet.firstName'));  
  this.lastNameEl = element(by.binding('vet.lastName'));  
  this.specialitiesEl = element(by.binding('vet.specialities'));  
};

module.exports = new ViewPage();

