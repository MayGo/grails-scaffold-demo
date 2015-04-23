'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('divisionCollectionlessSubmitBtn'));

			this.nameEl = element(by.model('divisionCollectionless.name'));
		this.nameViewEl = element.all(by.binding('divisionCollectionless.name')).first();
		this.headDivisionEl = element(by.model('divisionCollectionless.headDivision'));
		this.headDivisionViewEl = element.all(by.binding('divisionCollectionless.headDivision')).first();

};

module.exports = new CreatePage();

