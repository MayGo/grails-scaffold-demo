'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('petTypeSubmitBtn'));

			this.nameEl = element(by.model('petType.name'));
		this.nameViewEl = element.all(by.binding('petType.name')).first();

};

module.exports = new CreatePage();

