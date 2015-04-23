'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('petSubmitBtn'));

			this.birthDateEl = element(by.model('pet.birthDate'));
		this.birthDateViewEl = element.all(by.binding('pet.birthDate')).first();
		this.nameEl = element(by.model('pet.name'));
		this.nameViewEl = element.all(by.binding('pet.name')).first();
		this.visitsEl = element(by.model('pet.visits'));
		this.visitsViewEl = element.all(by.binding('pet.visits')).first();
		this.typeEl = element(by.model('pet.type'));
		this.typeViewEl = element.all(by.binding('pet.type')).first();
		this.ownerEl = element(by.model('pet.owner'));
		this.ownerViewEl = element.all(by.binding('pet.owner')).first();

};

module.exports = new CreatePage();

