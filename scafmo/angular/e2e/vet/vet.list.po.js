/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.firstNameEl = element(by.model('search.firstName'));  
  this.lastNameEl = element(by.model('search.lastName'));  
  this.specialitiesEl = element(by.model('search.specialities'));  
};

module.exports = new EditPage();

