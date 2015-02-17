'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('specialitySubmitBtn'));
  
  this.nameEl = element(by.model('speciality.name'));  
};

module.exports = new CreatePage();

