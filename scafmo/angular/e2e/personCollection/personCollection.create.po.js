'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('personCollectionSubmitBtn'));

			this.ageEl = element(by.model('personCollection.age'));
		this.ageViewEl = element.all(by.binding('personCollection.age')).first();
		this.nameEl = element(by.model('personCollection.name'));
		this.nameViewEl = element.all(by.binding('personCollection.name')).first();
		this.divisionEl = element(by.model('personCollection.division'));
		this.divisionViewEl = element.all(by.binding('personCollection.division')).first();

};

module.exports = new CreatePage();

