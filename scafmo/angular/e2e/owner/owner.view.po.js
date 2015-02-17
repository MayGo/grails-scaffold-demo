/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var ViewPage = function() {
  
  this.addressEl = element(by.binding('owner.address'));  
  this.cityEl = element(by.binding('owner.city'));  
  this.firstNameEl = element(by.binding('owner.firstName'));  
  this.lastNameEl = element(by.binding('owner.lastName'));  
  this.telephoneEl = element(by.binding('owner.telephone'));  
  this.petsEl = element(by.binding('owner.pets'));  
};

module.exports = new ViewPage();

