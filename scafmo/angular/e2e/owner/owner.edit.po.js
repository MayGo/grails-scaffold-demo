/**
 * This file uses the Page Object pattern to define the main page for tests
 * https://docs.google.com/presentation/d/1B6manhG0zEXkC-H-tPo2vwU06JhL8w9-XCF9oehXzAQ
 */

'use strict';




var EditPage = function() {
  
  this.addressEl = element(by.model('owner.address'));  
  this.cityEl = element(by.model('owner.city'));  
  this.firstNameEl = element(by.model('owner.firstName'));  
  this.lastNameEl = element(by.model('owner.lastName'));  
  this.telephoneEl = element(by.model('owner.telephone'));  
  this.petsEl = element(by.model('owner.pets'));  
};

module.exports = new EditPage();

