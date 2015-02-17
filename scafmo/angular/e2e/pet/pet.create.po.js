'use strict';




var CreatePage = function() {
  this.submitButton = element(by.id('petSubmitBtn'));
  
  this.birthDateEl = element(by.model('pet.birthDate'));  
  this.nameEl = element(by.model('pet.name'));  
  this.visitsEl = element(by.model('pet.visits'));  
  this.typeEl = element(by.model('pet.type'));  
  this.ownerEl = element(by.model('pet.owner'));  
};

module.exports = new CreatePage();

