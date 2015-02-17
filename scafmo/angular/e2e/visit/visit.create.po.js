'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('visitSubmitBtn'));
  
  this.dateEl = element(by.model('visit.date'));  
  this.descriptionEl = element(by.model('visit.description'));  
  this.petEl = element(by.model('visit.pet'));  
};

module.exports = new CreatePage();

