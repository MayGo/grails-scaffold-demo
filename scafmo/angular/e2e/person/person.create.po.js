'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('personSubmitBtn'));

			this.firstNameEl = element(by.model('person.firstName'));
		this.firstNameViewEl = element.all(by.binding('person.firstName')).first();
		this.lastNameEl = element(by.model('person.lastName'));
		this.lastNameViewEl = element.all(by.binding('person.lastName')).first();

};

module.exports = new CreatePage();

