'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('vetSubmitBtn'));

			this.firstNameEl = element(by.model('vet.firstName'));
		this.firstNameViewEl = element.all(by.binding('vet.firstName')).first();
		this.lastNameEl = element(by.model('vet.lastName'));
		this.lastNameViewEl = element.all(by.binding('vet.lastName')).first();
		this.specialitiesEl = element(by.css('#specialities .input'));
		this.specialitiesViewEl = element.all(by.repeater('item in vet.specialities'));

};

module.exports = new CreatePage();

