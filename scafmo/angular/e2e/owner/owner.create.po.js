'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('ownerSubmitBtn'));
  
  this.addressEl = element(by.model('owner.address'));  
  this.cityEl = element(by.model('owner.city'));  
  this.firstNameEl = element(by.model('owner.firstName'));  
  this.lastNameEl = element(by.model('owner.lastName'));  
  this.telephoneEl = element(by.model('owner.telephone'));  
  this.petsEl = element(by.model('owner.pets'));  
};

module.exports = new CreatePage();

