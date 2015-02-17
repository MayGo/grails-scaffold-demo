'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('divisionCollectionlessSubmitBtn'));
  
  this.nameEl = element(by.model('divisionCollectionless.name'));  
  this.headDivisionEl = element(by.model('divisionCollectionless.headDivision'));  
};

module.exports = new CreatePage();

