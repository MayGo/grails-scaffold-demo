/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.birthDateEl = element(by.model('pet.birthDate'));  
  this.nameEl = element(by.model('pet.name'));  
  this.visitsEl = element(by.model('pet.visits'));  
  this.typeEl = element(by.model('pet.type'));  
  this.ownerEl = element(by.model('pet.owner'));  
};

module.exports = new EditPage();

