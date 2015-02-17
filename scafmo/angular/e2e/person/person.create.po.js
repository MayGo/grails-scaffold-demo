'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('personSubmitBtn'));
  
  this.firstNameEl = element(by.model('person.firstName'));  
  this.lastNameEl = element(by.model('person.lastName'));  
};

module.exports = new CreatePage();

