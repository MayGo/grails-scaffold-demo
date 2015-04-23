'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('ownerSubmitBtn'));

			this.addressEl = element(by.model('owner.address'));
		this.addressViewEl = element.all(by.binding('owner.address')).first();
		this.cityEl = element(by.model('owner.city'));
		this.cityViewEl = element.all(by.binding('owner.city')).first();
		this.firstNameEl = element(by.model('owner.firstName'));
		this.firstNameViewEl = element.all(by.binding('owner.firstName')).first();
		this.lastNameEl = element(by.model('owner.lastName'));
		this.lastNameViewEl = element.all(by.binding('owner.lastName')).first();
		this.telephoneEl = element(by.model('owner.telephone'));
		this.telephoneViewEl = element.all(by.binding('owner.telephone')).first();
		this.petsEl = element(by.model('owner.pets'));
		this.petsViewEl = element.all(by.binding('owner.pets')).first();

};

module.exports = new CreatePage();

