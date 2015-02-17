/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.birthDateEl = element(by.binding('pet.birthDate'));  
  this.nameEl = element(by.binding('pet.name'));  
  this.visitsEl = element(by.binding('pet.visits'));  
  this.typeEl = element(by.binding('pet.type'));  
  this.ownerEl = element(by.binding('pet.owner'));  
};

module.exports = new ViewPage();

