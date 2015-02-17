'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('divisionCollectionSubmitBtn'));
  
  this.nameEl = element(by.model('divisionCollection.name'));  
  this.personsEl = element(by.model('divisionCollection.persons'));  
  this.headDivisionEl = element(by.model('divisionCollection.headDivision'));  
};

module.exports = new CreatePage();

