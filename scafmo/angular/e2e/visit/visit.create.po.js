'use strict';


var CreatePage = function() {
  this.submitButton = element(by.id('visitSubmitBtn'));

			this.dateEl = element(by.model('visit.date'));
		this.dateViewEl = element.all(by.binding('visit.date')).first();
		this.descriptionEl = element(by.model('visit.description'));
		this.descriptionViewEl = element.all(by.binding('visit.description')).first();
		this.petEl = element(by.model('visit.pet'));
		this.petViewEl = element.all(by.binding('visit.pet')).first();

};

module.exports = new CreatePage();

