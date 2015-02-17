'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('vetSubmitBtn'));
  
  this.firstNameEl = element(by.model('vet.firstName'));  
  this.lastNameEl = element(by.model('vet.lastName'));  
  this.specialitiesEl = element(by.model('vet.specialities'));  
};

module.exports = new CreatePage();

