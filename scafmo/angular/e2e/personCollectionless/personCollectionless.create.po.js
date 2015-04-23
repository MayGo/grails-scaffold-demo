'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('personCollectionlessSubmitBtn'));

			this.ageEl = element(by.model('personCollectionless.age'));
		this.ageViewEl = element.all(by.binding('personCollectionless.age')).first();
		this.nameEl = element(by.model('personCollectionless.name'));
		this.nameViewEl = element.all(by.binding('personCollectionless.name')).first();
		this.divisionEl = element(by.model('personCollectionless.division'));
		this.divisionViewEl = element.all(by.binding('personCollectionless.division')).first();

};

module.exports = new CreatePage();

