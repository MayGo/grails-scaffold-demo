/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.ageEl = element(by.model('personCollectionless.age'));  
  this.nameEl = element(by.model('personCollectionless.name'));  
  this.divisionEl = element(by.model('personCollectionless.division'));  
};

module.exports = new EditPage();

