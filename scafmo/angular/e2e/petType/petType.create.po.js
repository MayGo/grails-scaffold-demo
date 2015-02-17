'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('petTypeSubmitBtn'));
  
  this.nameEl = element(by.model('petType.name'));  
};

module.exports = new CreatePage();

