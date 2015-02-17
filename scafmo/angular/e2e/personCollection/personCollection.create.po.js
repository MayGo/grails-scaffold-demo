'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('personCollectionSubmitBtn'));
  
  this.ageEl = element(by.model('personCollection.age'));  
  this.nameEl = element(by.model('personCollection.name'));  
  this.divisionEl = element(by.model('personCollection.division'));  
};

module.exports = new CreatePage();

