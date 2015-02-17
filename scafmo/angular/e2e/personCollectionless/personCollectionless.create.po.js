'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('personCollectionlessSubmitBtn'));
  
  this.ageEl = element(by.model('personCollectionless.age'));  
  this.nameEl = element(by.model('personCollectionless.name'));  
  this.divisionEl = element(by.model('personCollectionless.division'));  
};

module.exports = new CreatePage();

