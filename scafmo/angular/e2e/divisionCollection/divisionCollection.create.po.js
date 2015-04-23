'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('divisionCollectionSubmitBtn'));

			this.nameEl = element(by.model('divisionCollection.name'));
		this.nameViewEl = element.all(by.binding('divisionCollection.name')).first();
		this.personsEl = element(by.model('divisionCollection.persons'));
		this.personsViewEl = element.all(by.binding('divisionCollection.persons')).first();
		this.headDivisionEl = element(by.model('divisionCollection.headDivision'));
		this.headDivisionViewEl = element.all(by.binding('divisionCollection.headDivision')).first();

};

module.exports = new CreatePage();

